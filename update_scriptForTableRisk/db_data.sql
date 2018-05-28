INSERT INTO status (id, name, setDoneStatus, setIdleStatus, color,
                    sortOrder, idle, setHandledStatus, isCopyStatus, setCancelledStatus) VALUES
(1, 'recorded', 0, 0, '#ffa500', 100, 0, 0, 0, 0);
INSERT INTO status (id, name, setDoneStatus, setIdleStatus, color,
                    sortOrder, idle, setHandledStatus, isCopyStatus, setCancelledStatus) VALUES
(2, 'recorded', 0, 0, '#ffa500', 100, 0, 0, 0, 0);

INSERT INTO criticality (id, name, value, color, sortOrder, idle) VALUES
(1, 'Low', 1, '#32cd32', 10, 0),
(2, 'Medium', 2, '#ffd700', 20, 0),
(3, 'High', 4, '#ff0000', 30, 0),
(4, 'Critical', 8, '#000000', 40, 0);

INSERT INTO type (id, scope, name, sortOrder, idle, color, idWorkflow, mandatoryDescription, mandatoryResultOnDone, mandatoryResourceOnHandled, lockHandled, lockDone, lockIdle, code, internalData, description, lockCancelled, showInFlash, idPlanningMode, mandatoryResolutionOnDone, lockSolved) VALUES
(1, 'Risk', 'Contractual', 10, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'CON', NULL, NULL, 1, 0, NULL, 0, 0),
(2, 'Risk', 'Operational', 20, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'OPE', NULL, NULL, 1, 0, NULL, 0, 0),
(3, 'Risk', 'Technical', 30, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'TEC', NULL, NULL, 1, 0, NULL, 0, 0),
(4, 'Issue', 'Technical issue', 10, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'TEC', NULL, NULL, 1, 0, NULL, 0, 0),
(5, 'Issue', 'Process non conformity', 30, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'PRO', NULL, NULL, 1, 0, NULL, 0, 0),
(6, 'Issue', 'Quality non conformity', 40, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'QUA', NULL, NULL, 1, 0, NULL, 0, 0),
(7, 'Issue', 'Process non appliability', 20, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'PRO', NULL, NULL, 1, 0, NULL, 0, 0),
(8, 'Issue', 'Customer complaint', 90, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'CUS', NULL, NULL, 1, 0, NULL, 0, 0),
(9, 'Issue', 'Delay non respect', 50, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'DEL', NULL, NULL, 1, 0, NULL, 0, 0),
(10, 'Issue', 'Resource management issue', 70, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'RES', NULL, NULL, 1, 0, NULL, 0, 0),
(12, 'Issue', 'Financial loss', 80, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'FIN', NULL, NULL, 1, 0, NULL, 0, 0),
(13, 'Message', 'ALERT', 10, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'ALE', NULL, NULL, 1, 0, NULL, 0, 0),
(14, 'Message', 'WARNING', 10, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'WAR', NULL, NULL, 1, 0, NULL, 0, 0),
(15, 'Message', 'INFO', 30, 0,NULL, 1, 0, 1, 1, 1, 1, 1, 'INF', NULL, NULL, 1, 0, NULL, 0, 0),
(16, 'Ticket', 'Incident', 10, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'INC', NULL, NULL, 1, 0, NULL, 0, 0),
(17, 'Ticket', 'Support / Assistance', 20, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'SUP', NULL, NULL, 1, 0, NULL, 0, 0),
(18, 'Ticket', 'Anomaly / Bug', 30, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'ANO', NULL, NULL, 1, 0, NULL, 0, 0),
(19, 'Activity', 'Development', 10, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'DEV', NULL, NULL, 1, 0, 1, 0, 0),
(20, 'Activity', 'Evolution', 20, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'EVO', NULL, NULL, 1, 0, 1, 0, 0),
(21, 'Activity', 'Management', 30, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'MAN', NULL, NULL, 1, 0, 1, 0, 0),
(22, 'Activity', 'Phase', 40, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'PHA', NULL, NULL, 1, 0, 1, 0, 0),
(23, 'Milestone', 'Deliverable', 10, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'DEL', NULL, NULL, 1, 0, 5, 0, 0),
(24, 'Milestone', 'Incoming', 20, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'INC', NULL, NULL, 1, 0, 5, 0, 0),
(25, 'Milestone', 'Key date', 30, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'KEY', NULL, NULL, 1, 0, 5, 0, 0),
(26, 'Activity', 'Task', 1, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'TAS', NULL, NULL, 1, 0, 1, 0, 0),
(27, 'Action', 'Project', 10, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'PRO', NULL, NULL, 1, 0, NULL, 0, 0),
(28, 'Action', 'Internal', 20, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'INT', NULL, NULL, 1, 0, NULL, 0, 0),
(29, 'Action', 'Customer', 20, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 'CUS', NULL, NULL, 1, 0, NULL, 0, 0),
(30, 'Meeting', 'Steering Committee', 10, 0, NULL, 7, 0, 1, 1, 1, 1, 1, 'STE', NULL, NULL, 1, 0, 16, 0, 0),
(31, 'Meeting', 'Progress Metting', 20, 0, NULL, 7, 0, 1, 1, 1, 1, 1, 'PRO', NULL, NULL, 1, 0, 16, 0, 0),
(32, 'Meeting', 'Team meeting', 30, 0, NULL, 7, 0, 1, 1, 1, 1, 1, 'TEA', NULL, NULL, 1, 0, 16, 0, 0),
(33, 'Decision', 'Functional', 10, 0, NULL, 6, 0, 1, 1, 1, 1, 1, 'FUN', NULL, NULL, 1, 0, NULL, 0, 0),
(34, 'Decision', 'Operational', 20, 0, NULL, 6, 0, 1, 1, 1, 1, 1, 'OPE', NULL, NULL, 1, 0, NULL, 0, 0),
(35, 'Decision', 'Contractual', 30, 0, NULL, 6, 0, 1, 1, 1, 1, 1, 'CON', NULL, NULL, 1, 0, NULL, 0, 0),
(36, 'Decision', 'Strategic', 40, 0, NULL, 6, 0, 1, 1, 1, 1, 1, 'STR', NULL, NULL, 1, 0, NULL, 0, 0),
(37, 'Question', 'Functional', 10, 0, NULL, 5, 0, 1, 1, 1, 1, 1, 'FUN', NULL, NULL, 1, 0, NULL, 0, 0),
(38, 'Question', 'Technical', 20, 0, NULL, 5, 0, 1, 1, 1, 1, 1, 'TEC', NULL, NULL, 1, 0, NULL, 0, 0),
(39, 'IndividualExpense', 'Expense report', 10, 0, NULL, 8, 0, 0, 0, 0, 0, 0, 'EXP', NULL, NULL, 0, 0, NULL, 0, 0),
(40, 'ProjectExpense', 'Machine expense', 10, 0, NULL, 8, 0, 0, 0, 0, 0, 0, 'MAC', NULL, NULL, 0, 0, NULL, 0, 0),
(41, 'ProjectExpense', 'Office expense', 20, 0, NULL, 8, 0, 0, 0, 0, 0, 0, 'OFF', NULL, NULL, 0, 0, NULL, 0, 0),
(42, 'Invoice', 'event invoice', 10, 0, NULL, 8, 0, 0, 0, 0, 0, 0, 'EVE', NULL, NULL, 0, 0, NULL, 0, 0),
(43, 'Invoice', 'partial invoice', 20, 0, NULL, 8, 0, 0, 0, 0, 0, 0, 'PAR', NULL, NULL, 0, 0, NULL, 0, 0),
(44, 'Invoice', 'final invoice', 30, 0, NULL, 8, 0, 0, 0, 0, 0, 0, 'FIN', NULL, NULL, 0, 0, NULL, 0, 0),
(46, 'Payment', 'partial payment', 20, 0, NULL, 8, 0, 0, 0, 0, 0, 0, 'PAR', NULL, NULL, 0, 0, NULL, 0, 0),
(47, 'Payment', 'final payment', 10, 0, NULL, 8, 0, 0, 0, 0, 0, 0, 'FIN', NULL, NULL, 0, 0, NULL, 0, 0),
(48, 'Project', 'Fixed Price', 10, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'OPE', 'E', NULL, 0, 0, NULL, 0, 0),
(49, 'Project', 'Time & Materials', 20, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'OPE', 'R', NULL, 0, 0, NULL, 0, 0),
(50, 'Project', 'Capped Time & Materials', 30, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'OPE', 'P', NULL, 0, 0, NULL, 0, 0),
(51, 'Project', 'Internal', 40, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'OPE', 'N', NULL, 0, 0, NULL, 0, 0),
(52, 'Project', 'Administrative', 80, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'ADM', 'N', NULL, 0, 0, NULL, 0, 0),
(53, 'Project', 'Template', 90, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'TMP', 'N', NULL, 0, 0, NULL, 0, 0),
(54, 'Document', 'Need expression', 210, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'NEEDEXP', NULL, NULL, 0, 0, NULL, 0, 0),
(55, 'Document', 'General Specification', 220, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'GENSPEC', NULL, NULL, 0, 0, NULL, 0, 0),
(56, 'Document', 'Detailed Specification', 230, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'DETSPEC', NULL, NULL, 0, 0, NULL, 0, 0),
(57, 'Document', 'General Conception', 240, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'GENCON', NULL, NULL, 0, 0, NULL, 0, 0),
(58, 'Document', 'Detail Conception', 250, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'DETCON', NULL, NULL, 0, 0, NULL, 0, 0),
(59, 'Document', 'Test Plan', 260, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'TEST', NULL, NULL, 0, 0, NULL, 0, 0),
(60, 'Document', 'Installaton manual', 270, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'INST', NULL, NULL, 0, 0, NULL, 0, 0),
(61, 'Document', 'Exploitation manual', 280, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'EXPL', NULL, NULL, 0, 0, NULL, 0, 0),
(62, 'Document', 'User manual', 290, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'MANUAL', NULL, NULL, 0, 0, NULL, 0, 0),
(63, 'Document', 'Contract', 110, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'CTRCT', NULL, NULL, 0, 0, NULL, 0, 0),
(64, 'Document', 'Management', 120, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'MGT', NULL, NULL, 0, 0, NULL, 0, 0),
(65, 'Document', 'Meeting Review', 130, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'MEETREV', NULL, NULL, 0, 0, NULL, 0, 0),
(66, 'Document', 'Follow-up', 140, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'F-UP', NULL, NULL, 0, 0, NULL, 0, 0),
(67, 'Document', 'Financial', 150, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'FIN', NULL, NULL, 0, 0, NULL, 0, 0),
(68, 'Versioning', 'evolutive', 10, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'EVO', NULL, NULL, 0, 0, NULL, 0, 0),
(69, 'Versioning', 'chronological', 20, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'EVT', NULL, NULL, 0, 0, NULL, 0, 0),
(70, 'Versioning', 'sequential', 30, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'SEQ', NULL, NULL, 0, 0, NULL, 0, 0),
(71, 'Versioning', 'external', 40, 0, NULL, 1, 0, 0, 0, 0, 0, 0, 'EXT', NULL, NULL, 0, 0, NULL, 0, 0),
(72, 'Bill', 'Partial bill', 100, 0, NULL, 1, 0, 0, 0, 0, 1, 1, 'PARTIAL', NULL, NULL, 1, 0, NULL, 0, 0),
(73, 'Bill', 'Final bill', 200, 0, NULL, 1, 0, 0, 0, 0, 1, 1, 'FINAL', NULL, NULL, 1, 0, NULL, 0, 0),
(74, 'Bill', 'Complete bill', 300, 0, NULL, 1, 0, 0, 0, 0, 1, 1, 'COMPLETE', NULL, NULL, 1, 0, NULL, 0, 0),
(75, 'Requirement', 'Functional', 10, 0, NULL, 1, 1, 0, 1, 1, 1, 1, 'FUNC', NULL, NULL, 1, 0, NULL, 0, 0),
(76, 'Requirement', 'Technical', 20, 0, NULL, 1, 1, 0, 1, 1, 1, 1, 'TECH', NULL, NULL, 1, 0, NULL, 0, 0),
(77, 'Requirement', 'Security', 30, 0, NULL, 1, 1, 0, 1, 1, 1, 1, 'SECU', NULL, NULL, 1, 0, NULL, 0, 0),
(78, 'Requirement', 'Regulatory', 40, 0, NULL, 1, 1, 0, 1, 1, 1, 1, 'REGL', NULL, NULL, 1, 0, NULL, 0, 0),
(79, 'TestCase', 'Requirement test', 10, 0, NULL, 1, 1, 1, 1, 1, 1, 1, 'REQU', NULL, NULL, 1, 0, NULL, 0, 0),
(80, 'TestCase', 'Non regression', 30, 0, NULL, 1, 1, 1, 1, 1, 1, 1, 'NR', NULL, NULL, 1, 0, NULL, 0, 0),
(81, 'TestCase', 'Unit test', 20, 0, NULL, 1, 1, 1, 1, 1, 1, 1, 'UT', NULL, NULL, 1, 0, NULL, 0, 0),
(82, 'TestSession', 'Evolution test session', 10, 0, NULL, 1, 1, 1, 1, 1, 1, 1, 'EVO', NULL, NULL, 1, 0, 9, 0, 0),
(83, 'TestSession', 'Development test session', 20, 0, NULL, 1, 1, 1, 1, 1, 1, 1, 'DEV', NULL, NULL, 1, 0, 9, 0, 0),
(84, 'TestSession', 'Non regression test session', 30, 0, NULL, 1, 1, 1, 1, 1, 1, 1, 'NR', NULL, NULL, 1, 0, 9, 0, 0),
(85, 'TestSession', 'Unitary case test session', 40, 0, NULL, 1, 1, 1, 1, 1, 1, 1, 'UT', NULL, NULL, 1, 0, 9, 0, 0),
(86, 'Opportunity', 'Contractual', 10, 0, NULL, 1, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL, 0, 0, NULL, 0, 0),
(87, 'Opportunity', 'Operational', 20, 0, NULL, 1, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL, 0, 0, NULL, 0, 0),
(88, 'Opportunity', 'Technical', 30, 0, NULL, 1, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL, 0, 0, NULL, 0, 0),
(89, 'Command', 'Fixe Price', 10, 0, NULL, 1, 0, 0, 0, 0, 1, 1, '', NULL, NULL, 1, 0, NULL, 0, 0),
(90, 'Command', 'Per day', 20, 0, NULL, 1, 0, 0, 0, 0, 1, 1, '', NULL, NULL, 1, 0, NULL, 0, 0),
(91, 'Quotation', 'Fixe Price', 10, 0, NULL, 1, 0, 0, 0, 0, 1, 1, '', NULL, NULL, 0, 0, NULL, 0, 0),
(92, 'Quotation', 'Per day', 20, 0, NULL, 1, 0, 0, 0, 0, 1, 1, '', NULL, NULL, 0, 0, NULL, 0, 0),
(93, 'Quotation', 'Per month', 30, 0, NULL, 1, 0, 0, 0, 0, 1, 1, '', NULL, NULL, 0, 0, NULL, 0, 0),
(94, 'Quotation', 'Per year', 40, 0, NULL, 1, 0, 0, 0, 0, 1, 1, '', NULL, NULL, 0, 0, NULL, 0, 0),
(95, 'Command', 'Per month', 30, 0, NULL, 1, 0, 0, 0, 0, 1, 1, '', NULL, NULL, 0, 0, NULL, 0, 0),
(96, 'Command', 'Per year', 40, 0, NULL, 1, 0, 0, 0, 0, 1, 1, '', NULL, NULL, 0, 0, NULL, 0, 0),
(97, 'Client', 'business prospect', 10, 0, NULL, 1, 0, 0, 0, 0, 1, 1, '', NULL, NULL, 0, 0, NULL, 0, 0),
(98, 'Client', 'customer', 10, 0, NULL, 1, 0, 0, 0, 0, 1, 1, '', NULL, NULL, 0, 0, NULL, 0, 0),
(99, 'Product', 'software', 10, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(100, 'Product', 'service', 20, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(101, 'Component', 'specific', 10, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(102, 'Component', 'on the shelf', 20, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(103, 'Provider', 'wholesaler', 10, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(104, 'Provider', 'retailer', 20, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(105, 'Provider', 'service provider', 30, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(106, 'Provider', 'subcontractor', 40, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(107, 'Provider', 'central purchasing', 50, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(108, 'CallForTender', 'by mutual agreement', 10, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(109, 'CallForTender', 'adapted procedure', 20, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(110, 'CallForTender', 'open call for tender', 30, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(111, 'CallForTender', 'restricted call for tender', 40, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(112, 'Tender', 'by mutual agreement', 10, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(113, 'Tender', 'adapted procedure', 20, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(114, 'Tender', 'open call for tender', 30, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0),
(115, 'Tender', 'restricted call for tender', 40, 0, NULL, 1, 0, 0, 0, 0, 0, 0, '', NULL, NULL, 0, 0, NULL, 0, 0);

INSERT INTO likelihood (id, name, value, color, sortOrder, idle, valuePct) VALUES
(1, 'Low (10%)', 1, '#32cd32', 10, 0, 10),
(2, 'Medium (50%)', 2, '#ffd700', 20, 0, 50),
(3, 'High (90%)', 4, '#ff0000', 30, 0, 90);

INSERT INTO priority (id, name, value, color, sortOrder, idle) VALUES
(1, 'Low priority', 1, '#32cd32', 40, 0),
(2, 'Medium priority', 2, '#ffd700', 30, 0),
(3, 'High priority', 4, '#ff0000', 20, 0),
(4, 'Critical priority', 8, '#000000', 10, 0);

INSERT INTO severity (id, name, value, color, sortOrder, idle) VALUES
(1, 'Low', 1, '#32cd32', 10, 0),
(2, 'Medium', 2, '#ffd700', 20, 0),
(3, 'High', 4, '#ff0000', 30, 0);

--insert to demo
INSERT INTO risk (id, idproject, name, description, idrisktype, cause, impact, idseverity, idlikelihood, idcriticality, creationdate, iduser, idstatus, idresource, initialenddate, actualenddate, idledate, result, comment, idle, done, donedate,handled, handleddate, reference,externalreference, idpriority,cancelled, impactcost,projectreserveamount, mitigationplan) VALUES
	(1, 1,'demo1', 'nothing', 1, 'nothing', 'nothing', 1, 1, 1, NULL, 1, 1, 1, NULL,NULL, NUll, NULL, NULL, 1, 0, NULL, 0, NULL, NULL, NULL, 1,1, 0.00, 0.00, 'hi');
INSERT INTO risk (id, idproject, name, description, idrisktype, cause, impact, idseverity, idlikelihood, idcriticality, creationdate, iduser, idstatus, idresource, initialenddate, actualenddate, idledate, result, comment, idle, done, donedate,handled, handleddate, reference,externalreference, idpriority,cancelled, impactcost,projectreserveamount, mitigationplan) VALUES
	(2, 2,'demo2', 'Moring', 1, 'nothing', 'nothing', 2, 2, 2, NULL, 1, 2, 2, NULL,NULL, NUll, NULL, NULL, 2, 0, NULL, 0, NULL, NULL, NULL, 2,2, 0.00, 0.00, 'hello');