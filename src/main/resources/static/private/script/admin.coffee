menu = ->
	$("#menu-toggle").click (e) ->
		e.preventDefault()
		$('#wrapper').toggleClass('toggled')
		$('#menu-toggle').toggleClass('folded')
		if $("#menu-toggle").hasClass('folded')
			$("#menu-toggle>i").removeClass('fa-angle-double-left')
			$("#menu-toggle>i").addClass('fa-angle-double-right')
		else
			$("#menu-toggle>i").addClass('fa-angle-double-left')
			$("#menu-toggle>i").removeClass('fa-angle-double-right')

sidebar = ->
	p = $(".sidebar-nav").find("li a")

	items = {}
	['console', 'users', 'create_user', 'tasks', 'create_task', 'tokens'].forEach (x, i) -> items[x] = p[i]
	$(items[$.app.page]).addClass('act')

window.page = (index) -> $.app.params.set('page', index).go()

$ ->
	menu()
	sidebar()

$(document).on "keypress", "form", (event) -> return event.keyCode != 13
