INSERT INTO public.statuses(name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, created_by, created_date, modified_by, modified_date)
	VALUES ('recorded', '0', '0', '#ffa500', 100, '0', '0', '0', '0', 1, now() + '-1d' + '1d', 1, now() + '-1d' + '1d');
INSERT INTO public.statuses(name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, created_by, created_date, modified_by, modified_date)
	VALUES ('qualified', '0', '0', '#87ceeb', 200, '0', '0', '0', '0', 1, now() + '-1d' + '2d', 1, now() + '-1d' + '2d');
INSERT INTO public.statuses(name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, created_by, created_date, modified_by, modified_date)
	VALUES ('in progress', '0', '0', '#d2691e', 300, '0', '1', '0', '0', 1, now() + '-1d' + '3d', 1, now() + '-1d' + '3d');
INSERT INTO public.statuses(name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, created_by, created_date, modified_by, modified_date)
	VALUES ('done', '1', '0', '#afeeee', 400, '0', '1', '0', '0', 1, now() + '-1d' + '4d', 1, now() + '-1d' + '4d');
INSERT INTO public.statuses(name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, created_by, created_date, modified_by, modified_date)
	VALUES ('verified', '1', '0', '#32cd32', 500, '0', '1', '0', '0', 1, now() + '-1d' + '5d', 1, now() + '-1d' + '5d');
INSERT INTO public.statuses(name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, created_by, created_date, modified_by, modified_date)
	VALUES ('delivered', '1', '0', '#4169e1', 600, '0', '1', '0', '0', 1, now() + '-1d' + '6d', 1, now() + '-1d' + '6d');
INSERT INTO public.statuses(name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, created_by, created_date, modified_by, modified_date)
	VALUES ('closed', '1', '1', '#c0c0c0', 700, '0', '1', '0', '0', 1, now() + '-1d' + '7d', 1, now() + '-1d' + '7d');
INSERT INTO public.statuses(name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, created_by, created_date, modified_by, modified_date)
	VALUES ('re-opened', '0', '0', '#ff0000', 250, '0', '0', '0', '0', 1, now() + '-1d' + '8d', 1, now() + '-1d' + '8d');
INSERT INTO public.statuses(name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, created_by, created_date, modified_by, modified_date)
	VALUES ('cancelled', '1', '1', '#c0c0c0', 999, '0', '1', '0', '1', 1, now() + '-1d' + '9d', 1, now() + '-1d' + '9d');
INSERT INTO public.statuses(name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, created_by, created_date, modified_by, modified_date)
	VALUES ('assigned', '0', '0', '#8b4513', 275, '0', '1', '0', '0', 1, now() + '-1d' + '10d', 1, now() + '-1d' + '10d');
INSERT INTO public.statuses(name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, created_by, created_date, modified_by, modified_date)
	VALUES ('accepted', '0', '0', '#a52a2a', 220, '0', '0', '0', '0', 1, now() + '-1d' + '11d', 1, now() + '-1d' + '11d');
INSERT INTO public.statuses(name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, created_by, created_date, modified_by, modified_date)
	VALUES ('validated', '1', '0', '#98fb98', 650, '0', '1', '0', '0', 1, now() + '-1d' + '12d', 1, now() + '-1d' + '12d');
INSERT INTO public.statuses(name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, created_by, created_date, modified_by, modified_date)
	VALUES ('prepared', '0', '0', '#d2691e', 290, '0', '1', '0', '0', 1, now() + '-1d' + '13d', 1, now() + '-1d' + '13d');
INSERT INTO public.statuses(name, set_done_status, set_idle_status, color, sort_order, idle, set_handled_status, is_copy_status, set_cancelled_status, created_by, created_date, modified_by, modified_date)
	VALUES ('copied', '0', '0', '#ffffff', 999, '1', '0', '1', '0', 1, now() + '-1d' + '14d', 1, now() + '-1d' + '14d');