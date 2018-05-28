
DROP TABLE IF EXISTS workflow;
CREATE TABLE IF NOT EXISTS workflow (
  id char(100) PRIMARY KEY NOT NULL,
  name varchar(100) DEFAULT NULL,
  description text,
  idle bit(1) DEFAULT '0',
  workflowUpdate varchar(100) DEFAULT NULL,
  sortOrder bit(3) DEFAULT NULL
);

DROP TABLE IF EXISTS planning_mode;
CREATE TABLE IF NOT EXISTS planning_mode (
  id char(100) PRIMARY KEY NOT NULL,
  name varchar(100) DEFAULT NULL,
  code varchar(5) DEFAULT NULL,
  sortOrder bit(3) DEFAULT NULL,
  mandatoryStartDate bit(1) DEFAULT '0',
  mandatoryEndDate bit(1) DEFAULT '0',
  applyTo varchar(20) DEFAULT NULL,
  idle bit(1) DEFAULT '0',
  mandatoryDuration bit(1) DEFAULT '0'
  );


DROP TABLE IF EXISTS status;
CREATE TABLE IF NOT EXISTS status (
  id integer PRIMARY KEY NOT NULL,
  name varchar(100) DEFAULT NULL,
  setDoneStatus integer DEFAULT NULL,
  setIdleStatus integer DEFAULT NULL,
  color varchar(7) DEFAULT NULL,
  sortOrder integer DEFAULT NULL,
  idle integer DEFAULT NULL,
  setHandledStatus integer DEFAULT NULL,
  isCopyStatus integer DEFAULT NULL,
  setCancelledStatus integer DEFAULT NULL
);

--DROP TABLE IF EXISTS type;
--CREATE TABLE IF NOT EXISTS type (
--  id integer PRIMARY KEY NOT NULL,
--  scope varchar(100) DEFAULT NULL,
--  name varchar(100) DEFAULT NULL,
--  sortOrder integer DEFAULT NULL,
--  idle bit(1) DEFAULT '0',
--  color varchar(7) DEFAULT NULL,
--  idWorkflow integer DEFAULT NULL,
--  mandatoryDescription bit(1) DEFAULT '0',
--  mandatoryResultOnDone bit(1) DEFAULT '0',
--  mandatoryResourceOnHandled bit(1) DEFAULT '0',
--  lockHandled bit(1) DEFAULT '0',
--  lockDone bit(1) DEFAULT '0',
--  lockIdle bit(1) DEFAULT '0',
--  code varchar(10) DEFAULT NULL,
--  integerernalData varchar(1) DEFAULT NULL,
--  description text,
--  lockCancelled bit(1) DEFAULT '0',
--  showInFlash bit(1) DEFAULT '0',
--  idPlanningMode integer DEFAULT NULL,
--  mandatoryResolutionOnDone bit(1) DEFAULT '0',
--  lockSolved bit(1) DEFAULT '0'
--  );

DROP TABLE IF EXISTS type;
CREATE TABLE IF NOT EXISTS type (
  id integer PRIMARY KEY,
  scope varchar(100) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  sortOrder integer DEFAULT NULL,
  idle integer DEFAULT NULL,
  color varchar(7) DEFAULT NULL,
  idWorkflow integer DEFAULT NULL,
  mandatoryDescription integer DEFAULT NULL,
  mandatoryResultOnDone integer DEFAULT NULL,
  mandatoryResourceOnHandled integer DEFAULT NULL,
  lockHandled integer DEFAULT NULL,
  lockDone integer DEFAULT NULL,
  lockIdle integer DEFAULT NULL,
  code varchar(10) DEFAULT NULL,
  internalData varchar(1) DEFAULT NULL,
  description text,
  lockCancelled integer  DEFAULT NULL,
  showInFlash integer DEFAULT NULL,
  idPlanningMode integer  DEFAULT NULL,
  mandatoryResolutionOnDone integer DEFAULT NULL,
  lockSolved integer  DEFAULT NULL
);

DROP TABLE IF EXISTS call_for_tender;
CREATE TABLE IF NOT EXISTS call_for_tender (
  id char(100) PRIMARY KEY NOT NULL,
  reference varchar(100) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  idCallForTenderType integer DEFAULT NULL,
  idProject integer DEFAULT NULL,
  idUser integer DEFAULT NULL,
  description text,
  technicalRedefaulquirements text,
  businessRequirements text,
  otherRequirements text,
  creationDate timestamp default current_timestamp,
  idStatus integer DEFAULT NULL,
  idResource integer DEFAULT NULL,
  sendDateTime timestamp default current_timestamp,
  expectedTenderDateTime timestamp default current_timestamp,
  maxAmount decimal(11,2) DEFAULT NULL,
  deliveryDate timestamp default current_timestamp,
  evaluationMaxValue decimal(7,2) DEFAULT NULL,
  fixValue bit(1) DEFAULT '0',
  idProduct integer DEFAULT NULL,
  idProductVersion integer DEFAULT NULL,
  idComponent integer DEFAULT NULL,
  idComponentVersion integer DEFAULT NULL,
  result text,
  handled bit(1) DEFAULT '0',
  done bit(1) DEFAULT '0',
  idle bit(1) DEFAULT '0',
  cancelled bit(1) DEFAULT '0',
  handledDate timestamp default current_timestamp,
  doneDate timestamp default current_timestamp,
  idleDate timestamp default current_timestamp
);

DROP TABLE IF EXISTS product;
CREATE TABLE IF NOT EXISTS product (
  id char(100) PRIMARY KEY NOT NULL,
  name varchar(100) DEFAULT NULL,
  idClient integer DEFAULT NULL,
  idContact integer DEFAULT NULL,
  description text,
  creationDate timestamp default current_timestamp,
  idle bit(1) DEFAULT '0',
  idProduct integer DEFAULT NULL,
  designation varchar(100) DEFAULT NULL,
  scope varchar(100) DEFAULT NULL,
  idProductType integer DEFAULT NULL,
  idComponentType integer DEFAULT NULL,
  idResource integer DEFAULT NULL
);

DROP TABLE IF EXISTS version;
CREATE TABLE IF NOT EXISTS version (
  id char(100) PRIMARY KEY NOT NULL,
  idProductVersion integer DEFAULT NULL,
  idComponentVersion integer DEFAULT NULL,
  comment varchar(4000) DEFAULT NULL,
  creationDate timestamp default current_timestamp,
  idUser integer DEFAULT NULL,
  idle bit(1) DEFAULT '0'
);

DROP TABLE IF EXISTS tender;
CREATE TABLE IF NOT EXISTS tender (
  id char(100) PRIMARY KEY NOT NULL,
  reference varchar(100) DEFAULT NULL,
  name varchar(200) DEFAULT NULL,
  idTenderType integer DEFAULT NULL,
  idProject integer DEFAULT NULL,
  idCallForTender integer DEFAULT NULL,
  idTenderStatus integer DEFAULT NULL,
  idUser integer DEFAULT NULL,
  creationDate timestamp default current_timestamp,
  idProvider integer DEFAULT NULL,
  externalReference varchar(100) DEFAULT NULL,
  description text,
  idStatus integer DEFAULT NULL,
  idResource integer DEFAULT NULL,
  idContact integer DEFAULT NULL,
  requestDateTime timestamp default current_timestamp,
  expectedTenderDateTime timestamp default current_timestamp,
  receptionDateTime timestamp default current_timestamp,
  evaluationValue decimal(7,2) DEFAULT NULL,
  evaluationRank bit(3) DEFAULT NULL,
  offerValidityEndDate timestamp default current_timestamp,
  plannedAmount decimal(11,2) DEFAULT NULL,
  taxPct decimal(5,2) DEFAULT NULL,
  plannedTaxAmount decimal(11,2) DEFAULT NULL,
  plannedFullAmount decimal(11,2) DEFAULT NULL,
  initialAmount decimal(11,2) DEFAULT NULL,
  initialTaxAmount decimal(11,2) DEFAULT NULL,
  initialFullAmount decimal(11,2) DEFAULT NULL,
  deliveryDelay varchar(100) DEFAULT NULL,
  deliveryDate timestamp default current_timestamp,
  paymentCondition varchar(100) DEFAULT NULL,
  evaluation decimal(7,2) DEFAULT NULL,
  result text,
  handled bit(1) DEFAULT '0',
  done bit(1) DEFAULT '0',
  idle bit(1) DEFAULT '0',
  cancelled bit(1) DEFAULT '0',
  handledDate timestamp default current_timestamp,
  doneDate timestamp default current_timestamp,
  idleDate timestamp default current_timestamp
);

DROP TABLE IF EXISTS provider;
CREATE TABLE IF NOT EXISTS provider (
  id char(100) PRIMARY KEY NOT NULL,
  name varchar(100) NOT NULL,
  idProviderType integer DEFAULT NULL,
  description text,
  providerCode varchar(25) DEFAULT NULL,
  idPaymentDelay integer DEFAULT NULL,
  numTax varchar(100) DEFAULT NULL,
  tax decimal(5,2) DEFAULT NULL,
  designation varchar(100) DEFAULT NULL,
  street varchar(100) DEFAULT NULL,
  complement varchar(100) DEFAULT NULL,
  zip varchar(100) DEFAULT NULL,
  city varchar(100) DEFAULT NULL,
  state varchar(100) DEFAULT NULL,
  country varchar(100) DEFAULT NULL,
  idle bit(1) DEFAULT '0'
  );

DROP TABLE IF EXISTS resource;
CREATE TABLE IF NOT EXISTS resource (
  id char(100) PRIMARY KEY NOT NULL,
  name varchar(100) DEFAULT NULL,
  fullName varchar(100) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  description text,
  password varchar(100) DEFAULT NULL,
  idProfile integer DEFAULT NULL,
  isResource bit(1) DEFAULT '0',
  isUser bit(1) DEFAULT '0',
  locked bit(1) DEFAULT '0',
  idle bit(1) DEFAULT '0',
  phone varchar(20) DEFAULT NULL,
  mobile varchar(20) DEFAULT NULL,
  fax varchar(20) DEFAULT NULL,
  idTeam integer DEFAULT NULL,
  capacity decimal(5,2) DEFAULT '1.00',
  address varchar(4000) DEFAULT NULL,
  isContact bit(1) DEFAULT '0',
  idClient integer DEFAULT NULL,
  idRole integer DEFAULT NULL,
  isLdap bit(1) DEFAULT '0',
  initials varchar(10) DEFAULT NULL,
  designation varchar(50) DEFAULT NULL,
  street varchar(50) DEFAULT NULL,
  complement varchar(50) DEFAULT NULL,
  zip varchar(50) DEFAULT NULL,
  city varchar(50) DEFAULT NULL,
  state varchar(50) DEFAULT NULL,
  country varchar(50) DEFAULT NULL,
  idRecipient integer DEFAULT NULL,
  loginTry integer DEFAULT '0',
  salt varchar(100) DEFAULT NULL,
  crypto varchar(100) DEFAULT 'md5',
  idCalendarDefinition integer DEFAULT '1',
  cookieHash varchar(400) DEFAULT NULL,
  passwordChangeDate timestamp default current_timestamp,
  apiKey varchar(400) DEFAULT NULL,
  dontReceiveTeamMails bit(1) DEFAULT '0',
  function varchar(100) DEFAULT NULL,
  idProvider integer DEFAULT NULL
);

DROP TABLE IF EXISTS expense;
CREATE TABLE IF NOT EXISTS expense (
  id char(100) PRIMARY KEY NOT NULL,
  idProject integer DEFAULT NULL,
  idResource integer DEFAULT NULL,
  idUser integer DEFAULT NULL,
  idExpenseType integer DEFAULT NULL,
  scope varchar(100) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  idStatus integer DEFAULT NULL,
  description text,
  expensePlannedDate timestamp default current_timestamp,
  expenseRealDate timestamp default current_timestamp,
  plannedAmount decimal(11,2) DEFAULT NULL,
  realAmount decimal(11,2) DEFAULT NULL,
  day varchar(8) DEFAULT NULL,
  week varchar(6) DEFAULT NULL,
  month varchar(6) DEFAULT NULL,
  year varchar(4) DEFAULT NULL,
  idle bit(1) DEFAULT '0',
  reference varchar(100) DEFAULT NULL,
  externalReference varchar(100) DEFAULT NULL,
  cancelled bit(1) DEFAULT '0',
  idDocument integer DEFAULT NULL,
  idProvider integer DEFAULT NULL,
  sendDate timestamp default current_timestamp,
  idDeliveryMode integer DEFAULT NULL,
  deliveryDelay varchar(100) DEFAULT NULL,
  deliveryDate timestamp default current_timestamp,
  paymentCondition varchar(100) DEFAULT NULL,
  receptionDate timestamp default current_timestamp,
  result text,
  taxPct decimal(5,2) DEFAULT NULL,
  plannedFullAmount decimal(11,2) DEFAULT '0.00',
  realFullAmount decimal(11,2) DEFAULT '0.00',
  idleDate timestamp default current_timestamp,
  handled bit(1) DEFAULT '0',
  handledDate timestamp default current_timestamp,
  done bit(1) DEFAULT '0',
  doneDate timestamp default current_timestamp,
  idResponsible integer DEFAULT NULL,
  paymentDone bit(1) DEFAULT '0',
  idContact integer DEFAULT NULL
);

DROP TABLE IF EXISTS quotation;
CREATE TABLE IF NOT EXISTS quotation (
  id char(100) PRIMARY KEY NOT NULL,
  idProject integer DEFAULT NULL,
  idQuotationType integer DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  description text,
  creationDate timestamp default current_timestamp,
  idUser integer DEFAULT NULL,
  idStatus integer DEFAULT NULL,
  idResource integer DEFAULT NULL,
  idClient integer DEFAULT NULL,
  idContact integer DEFAULT NULL,
  additionalInfo text,
  initialEndDate timestamp default current_timestamp,
  untaxedAmount decimal(12,2) DEFAULT NULL,
  initialPricePerDayAmount decimal(12,2) DEFAULT '0.00',
  initialAmount decimal(12,2) DEFAULT '0.00',
  comment text,
  idle bit(1) DEFAULT '0',
  done bit(1) DEFAULT '0',
  cancelled bit(1) DEFAULT '0',
  idleDate timestamp default current_timestamp,
  doneDate timestamp default current_timestamp,
  handled bit(1) DEFAULT '0',
  handledDate timestamp default current_timestamp,
  reference varchar(100) DEFAULT NULL,
  sendDate timestamp default current_timestamp,
  validityEndDate timestamp default current_timestamp,
  idActivityType integer DEFAULT NULL,
  result text,
  idPaymentDelay integer DEFAULT NULL,
  tax decimal(5,2) DEFAULT NULL,
  fullAmount decimal(12,2) DEFAULT NULL,
  idDeliveryMode integer DEFAULT NULL,
  idLikelihood integer DEFAULT NULL,
  plannedWork decimal(12,2) DEFAULT '0.00'
);

DROP TABLE IF EXISTS payment_delay;
CREATE TABLE IF NOT EXISTS payment_delay (
  id char(100) PRIMARY KEY NOT NULL,
  name varchar(100) DEFAULT NULL,
  days bit(3) DEFAULT NULL,
  endOfMonth bit(1) DEFAULT '0',
  sortOrder bit(3) DEFAULT '0',
  idle bit(1) DEFAULT '0'
);

DROP TABLE IF EXISTS payment_mode;
CREATE TABLE IF NOT EXISTS payment_mode (
  id char(100) PRIMARY KEY NOT NULL,
  name varchar(100) DEFAULT NULL,
  sortOrder bit(3) DEFAULT '0',
  idle bit(1) DEFAULT '0'
);

DROP TABLE IF EXISTS term;
CREATE TABLE IF NOT EXISTS term (
  id char(100) PRIMARY KEY NOT NULL,
  name varchar(100) DEFAULT NULL,
  idProject integer DEFAULT NULL,
  amount decimal(10,2) DEFAULT NULL,
  validatedAmount decimal(10,2) DEFAULT NULL,
  plannedAmount decimal(10,2) DEFAULT NULL,
  date timestamp default current_timestamp,
  validatedDate timestamp default current_timestamp,
  plannedDate timestamp default current_timestamp,
  idle bit(1) DEFAULT NULL,
  idBill integer DEFAULT NULL,
  idUser integer DEFAULT NULL,
  creationDate timestamp default current_timestamp
);

DROP TABLE IF EXISTS bill;
CREATE TABLE IF NOT EXISTS bill (
  id char(100) PRIMARY KEY NOT NULL,
  idBillType integer DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  idProject integer DEFAULT NULL,
  idClient integer DEFAULT NULL,
  idContact integer DEFAULT NULL,
  idRecipient integer DEFAULT NULL,
  billingType varchar(10) DEFAULT NULL,
  description text,
  date timestamp default current_timestamp,
  idStatus integer DEFAULT NULL,
  done bit(1) DEFAULT '0',
  idle bit(1) DEFAULT '0',
  billId integer DEFAULT NULL,
  tax decimal(5,2) DEFAULT NULL,
  untaxedAmount decimal(12,2) DEFAULT NULL,
  fullAmount decimal(12,2) DEFAULT NULL,
  cancelled bit(1) DEFAULT '0',
  idActivityType integer DEFAULT NULL,
  reference varchar(100) DEFAULT NULL,
  paymentDone bit(1) DEFAULT '0',
  paymentDate timestamp default current_timestamp,
  paymentAmount decimal(11,2) DEFAULT NULL,
  idPaymentDelay integer DEFAULT NULL,
  paymentDueDate timestamp default current_timestamp,
  idDeliveryMode integer DEFAULT NULL,
  idResource integer DEFAULT NULL,
  idUser integer DEFAULT NULL,
  creationDate timestamp default current_timestamp,
  paymentsCount bit(3) DEFAULT '0',
  commandAmountPct bit(3) DEFAULT '100',
  sendDate timestamp default current_timestamp
);

DROP TABLE IF EXISTS bill_line;
CREATE TABLE IF NOT EXISTS bill_line (
  id char(100) PRIMARY KEY NOT NULL,
  line bit(3) DEFAULT NULL,
  quantity decimal(9,2) DEFAULT NULL,
  description varchar(200) DEFAULT NULL,
  detail varchar(4000) DEFAULT NULL,
  price decimal(10,2) DEFAULT NULL,
  amount decimal(12,2) DEFAULT NULL,
  refType varchar(100) NOT NULL,
  refId integer NOT NULL,
  idTerm integer DEFAULT NULL,
  idResource integer DEFAULT NULL,
  idActivityPrice integer DEFAULT NULL,
  startDate timestamp default current_timestamp,
  endDate timestamp default current_timestamp,
  idMeasureUnit integer DEFAULT NULL,
  extra bit(1) DEFAULT '0',
  billingType varchar(10) DEFAULT NULL
);

DROP TABLE IF EXISTS client;
CREATE TABLE IF NOT EXISTS client (
  id char(100) PRIMARY KEY NOT NULL,
  name varchar(100) NOT NULL,
  description text,
  clientCode varchar(25) DEFAULT NULL,
  idle bit(1) DEFAULT '0',
  paymentDelay bit(3) DEFAULT NULL,
  tax decimal(5,2) DEFAULT NULL,
  designation varchar(100) DEFAULT NULL,
  street varchar(100) DEFAULT NULL,
  complement varchar(100) DEFAULT NULL,
  zip varchar(50) DEFAULT NULL,
  city varchar(100) DEFAULT NULL,
  state varchar(100) DEFAULT NULL,
  country varchar(100) DEFAULT NULL,
  idClientType integer DEFAULT NULL,
  paymentDelayEndOfMonth bit(1) DEFAULT '0',
  numTax varchar(100) DEFAULT NULL,
  idPaymentDelay integer DEFAULT NULL
  );

DROP TABLE IF EXISTS activity_price;
CREATE TABLE IF NOT EXISTS activity_price (
  id char(100) PRIMARY KEY NOT NULL,
  idProject integer DEFAULT NULL,
  idActivityType integer DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  priceCost decimal(10,2) DEFAULT '0.00',
  subcontractor bit(1) DEFAULT NULL,
  sortOrder bit(3) DEFAULT NULL,
  idle bit(1) DEFAULT '0',
  subcontractorCost decimal(10,2) DEFAULT NULL,
  idTeam integer DEFAULT NULL,
  commissionCost decimal(10,2) DEFAULT NULL,
  isRef bit(1) NOT NULL DEFAULT '0',
  pct bit(3) DEFAULT NULL,
  idUser integer DEFAULT NULL,
  creationDate timestamp default current_timestamp
);


DROP TABLE IF EXISTS priority;
CREATE TABLE IF NOT EXISTS public.priority (
  id integer PRIMARY KEY NOT NULL,
  name varchar(100) DEFAULT NULL,
  value integer DEFAULT NULL,
  color varchar(7) DEFAULT NULL,
  sortOrder integer  DEFAULT NULL,
  idle integer  DEFAULT '0'
);
DROP TABLE IF EXISTS likelihood;
CREATE TABLE IF NOT EXISTS likelihood (
  id integer PRIMARY KEY NOT NULL,
  name varchar(100) DEFAULT NULL,
  value integer  DEFAULT NULL,
  color varchar(7) DEFAULT NULL,
  sortOrder integer  DEFAULT NULL,
  idle integer DEFAULT '0',
  valuePcT integer DEFAULT '0'
);
DROP TABLE IF EXISTS severity;
CREATE TABLE IF NOT EXISTS severity (
  id integer PRIMARY KEY NOT NULL,
  name varchar(100) DEFAULT NULL,
  value integer  DEFAULT NULL,
  color varchar(7) DEFAULT NULL,
  sortOrder integer  DEFAULT NULL,
  idle  integer  DEFAULT '0'

) ;
DROP TABLE IF EXISTS criticality;
CREATE TABLE IF NOT EXISTS criticality (
  id integer PRIMARY KEY NOT NULL,
  name varchar(100) DEFAULT NULL,
  value integer  DEFAULT NULL,
  color varchar(7) DEFAULT NULL,
  sortOrder integer  DEFAULT NULL,
  idle integer  DEFAULT '0'
);

DROP TABLE IF EXISTS risk;
CREATE TABLE IF NOT EXISTS risk (
  id integer PRIMARY KEY  NOT NULL,
  idProject integer  DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  description text,
  idRiskType integer  DEFAULT NULL,
  cause text,
  impact text,
  idSeverity integer DEFAULT NULL,
  idLikelihood integer DEFAULT NULL,
  idCriticality integer  DEFAULT NULL,
  creationDate date DEFAULT NULL,
  idUser integer  DEFAULT NULL,
  idStatus integer  DEFAULT NULL,
  idResource integer  DEFAULT NULL,
  initialEndDate date DEFAULT NULL,
  actualEndDate date DEFAULT NULL,
  idleDate date DEFAULT NULL,
  result text,
  comment varchar(4000) DEFAULT NULL,
  idle integer  DEFAULT NULL,
  done integer  DEFAULT NULL,
  doneDate date DEFAULT NULL,
  handled integer  DEFAULT NULL,
  handledDate date DEFAULT NULL,
  reference varchar(100) DEFAULT NULL,
  externalReference varchar(100) DEFAULT NULL,
  idPriority integer  DEFAULT NULL,
  cancelled integer  DEFAULT NULL,
  impactCost decimal(11,2)  DEFAULT '0.00',
  projectReserveAmount decimal(11,2)  DEFAULT '0.00',
  mitigationPlan text  DEFAULT NULL
);