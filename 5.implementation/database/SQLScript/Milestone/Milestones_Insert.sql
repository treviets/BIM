DELETE FROM public.milestones;

INSERT INTO public.milestones
	(project_id, name, description, creation_date, user_id, status_id, resource_id, result, comment, idle, milestone_type_id, activity_id, done, idle_date, done_date, handled, handled_date, version_id, reference, external_reference, cancelled, client_id)
	VALUES (1, concat('name 1', ' ', RPAD('', 1, 'z')), concat('description 1', ' ', RPAD('', 1, 'w')), now() + '-1d' + '1d', 1, 1, 1, 1, 'comment 1', '1', 1, 1, '1', now() + '2d' + '1d', now() + '5d' + '1d', '1', now() + '3d' + '1d', 1, 1, 1, '1', 1);

INSERT INTO public.milestones
	(project_id, name, description, creation_date, user_id, status_id, resource_id, result, comment, idle, milestone_type_id, activity_id, done, idle_date, done_date, handled, handled_date, version_id, reference, external_reference, cancelled, client_id)
	VALUES (1, concat('name 2', ' ', RPAD('', 2, 'z')), concat('description 2', ' ', RPAD('', 2, 'w')), now() + '-1d' + '2d', 1, 1, 1, 1, 'comment 2', '1', 1, 1, '1', now() + '2d' + '2d', now() + '5d' + '2d', '1', now() + '3d' + '2d', 1, 1, 1, '1', 1);

INSERT INTO public.milestones
	(project_id, name, description, creation_date, user_id, status_id, resource_id, result, comment, idle, milestone_type_id, activity_id, done, idle_date, done_date, handled, handled_date, version_id, reference, external_reference, cancelled, client_id)
	VALUES (1, concat('name 3', ' ', RPAD('', 3, 'z')), concat('description 3', ' ', RPAD('', 3, 'w')), now() + '-1d' + '3d', 1, 1, 1, 1, 'comment 3', '1', 1, 1, '1', now() + '2d' + '3d', now() + '5d' + '3d', '1', now() + '3d' + '3d', 1, 1, 1, '1', 1);

INSERT INTO public.milestones
	(project_id, name, description, creation_date, user_id, status_id, resource_id, result, comment, idle, milestone_type_id, activity_id, done, idle_date, done_date, handled, handled_date, version_id, reference, external_reference, cancelled, client_id)
	VALUES (1, concat('name 4', ' ', RPAD('', 4, 'z')), concat('description 4', ' ', RPAD('', 4, 'w')), now() + '-1d' + '4d', 1, 1, 1, 1, 'comment 4', '1', 1, 1, '1', now() + '2d' + '4d', now() + '5d' + '4d', '1', now() + '3d' + '4d', 1, 1, 1, '1', 1);

INSERT INTO public.milestones
	(project_id, name, description, creation_date, user_id, status_id, resource_id, result, comment, idle, milestone_type_id, activity_id, done, idle_date, done_date, handled, handled_date, version_id, reference, external_reference, cancelled, client_id)
	VALUES (1, concat('name 5', ' ', RPAD('', 5, 'z')), concat('description 5', ' ', RPAD('', 5, 'w')), now() + '-1d' + '5d', 1, 1, 1, 1, 'comment 5', '1', 1, 1, '1', now() + '2d' + '5d', now() + '5d' + '5d', '1', now() + '3d' + '5d', 1, 1, 1, '1', 1);

INSERT INTO public.milestones
	(project_id, name, description, creation_date, user_id, status_id, resource_id, result, comment, idle, milestone_type_id, activity_id, done, idle_date, done_date, handled, handled_date, version_id, reference, external_reference, cancelled, client_id)
	VALUES (1, concat('name 6', ' ', RPAD('', 6, 'z')), concat('description 6', ' ', RPAD('', 6, 'w')), now() + '-1d' + '6d', 1, 1, 1, 1, 'comment 6', '1', 1, 1, '1', now() + '2d' + '6d', now() + '5d' + '6d', '1', now() + '3d' + '6d', 1, 1, 1, '1', 1);

INSERT INTO public.milestones
	(project_id, name, description, creation_date, user_id, status_id, resource_id, result, comment, idle, milestone_type_id, activity_id, done, idle_date, done_date, handled, handled_date, version_id, reference, external_reference, cancelled, client_id)
	VALUES (1, concat('name 7', ' ', RPAD('', 7, 'z')), concat('description 7', ' ', RPAD('', 7, 'w')), now() + '-1d' + '7d', 1, 1, 1, 1, 'comment 7', '1', 1, 1, '1', now() + '2d' + '7d', now() + '5d' + '7d', '1', now() + '3d' + '7d', 1, 1, 1, '1', 1);

INSERT INTO public.milestones
	(project_id, name, description, creation_date, user_id, status_id, resource_id, result, comment, idle, milestone_type_id, activity_id, done, idle_date, done_date, handled, handled_date, version_id, reference, external_reference, cancelled, client_id)
	VALUES (1, concat('name 8', ' ', RPAD('', 8, 'z')), concat('description 8', ' ', RPAD('', 8, 'w')), now() + '-1d' + '8d', 1, 1, 1, 1, 'comment 8', '1', 1, 1, '1', now() + '2d' + '8d', now() + '5d' + '8d', '1', now() + '3d' + '8d', 1, 1, 1, '1', 1);

INSERT INTO public.milestones
	(project_id, name, description, creation_date, user_id, status_id, resource_id, result, comment, idle, milestone_type_id, activity_id, done, idle_date, done_date, handled, handled_date, version_id, reference, external_reference, cancelled, client_id)
	VALUES (1, concat('name 9', ' ', RPAD('', 9, 'z')), concat('description 9', ' ', RPAD('', 9, 'w')), now() + '-1d' + '9d', 1, 1, 1, 1, 'comment 9', '1', 1, 1, '1', now() + '2d' + '9d', now() + '5d' + '9d', '1', now() + '3d' + '9d', 1, 1, 1, '1', 1);
