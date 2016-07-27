/// <reference path="../../../typings/globals/react-global/index.d.ts" />
/// <reference path="./app.d.ts"/>

import * as $ from "jquery"
import * as React from "react";
import * as ReactDOM from "react-dom";
import * as constants from "./misc/constants"
import utils from "./misc/utils"
import { TodoItem } from "./components/todoItem";

class TodoApp extends React.Component<AppProps, AppState> {
	token: string;

	constructor(props) {
		super(props);
		this.state = {tasks: new Array<Task>()};
		this.token = $('.todoapp').attr("token");
		$.ajaxSetup({
			headers: { 'TOKEN': this.token }
		});
	}

	public componentDidMount() {
		this.sync();
		setInterval(this.sync.bind(this), 5000);
	}

	public sync() {
		$.ajax({
			url: this.props.url,
			dataType: 'json',
			type: "GET",
			cache: false,
			success: function (data) {
				this.setState({tasks: data});
			}.bind(this),
			error: function (xhr, status, err) {
			}.bind(this)
		});
	}

	public handleNewTaskKeyDown(event) {
		if (event.keyCode !== constants.ENTER_KEY) return;
		event.preventDefault();

		var val = ReactDOM.findDOMNode<HTMLInputElement>(this.refs["newField"]).value.trim();
		if(val) {
			this.create({ id: new Date().getTime(), title: val});
			ReactDOM.findDOMNode<HTMLInputElement>(this.refs["newField"]).value = "";
		}
	}

	public create(task: Task) {
		let snapshot = this.state.tasks;
		this.setState({tasks: snapshot.concat(task)});

		$.ajax({
			url: this.props.url,
			dataType: 'json',
			type: 'POST',
			data: {
				content: task.title
			},
			success: function(data: Task) {
				this.sync();
			}.bind(this),
			error: function(xhr, status, err) {
				this.setState({tasks: snapshot});
				console.error(this.props.url, status, err.toString());
			}.bind(this)
		});
	}

	public destroy(todo) {
		let snapshot = this.state.tasks;
		let result = snapshot.filter((p) => { return todo.id != p.id; });
		this.setState({tasks: result});

		$.ajax({
			url: utils.join(this.props.url, todo.id),
			dataType: 'json',
			type: 'DELETE',
			success: function(data) { }.bind(this),
			error: function(xhr, status, err) {
				this.setState({tasks: snapshot});
				console.error(this.props.url, status, err.toString());
			}.bind(this)
		});
	}

	render() {
		let todoItems = this.state.tasks.map(function (each) {
			return (
				<TodoItem
					key={each.id}
					todo={each}
				  onDestroy={this.destroy.bind(this, each)}
				/>
			);
		}, this);

		let main = (
			<section className="main">
				<input className="toggle-all" type="checkbox" />
				<ul className="todo-list">
					{todoItems}
				</ul>
			</section>
		);

		let footer = (
			<div className="footer">
				<span className="todo-count">{this.state.tasks.length} items left</span>
			</div>
		);


		return (
			<div>
				<header className="header">
					<h1>todos</h1>
					<input
						ref="newField"
						className="new-todo"
						placeholder="What needs to be done?"
						onKeyDown={ e => this.handleNewTaskKeyDown(e) }
						autoFocus={true}
					/>
				</header>
				{main}
				{footer}
			</div>
		);
	}
}

ReactDOM.render(
	<TodoApp url="/api/tasks" pollInterval={5000} />,
	$('.todoapp').get(0)
);
