ALTER TABLE SBI_ORG_UNIT_GRANT ADD IS_AVAILABLE NUMBER DEFAULT 1;

INSERT INTO  SBI_EXPORTERS (ENGINE_ID,DOMAIN_ID,DEFAULT_VALUE)values((SELECT ENGINE_ID FROM SBI_ENGINES WHERE LABEL='WorksheetEngine'),(SELECT VALUE_ID FROM SBI_DOMAINS WHERE DOMAIN_CD = 'EXPORT_TYPE' AND VALUE_CD = 'PDF'), false);