UPDATE COUNTRY
SET COUNTRY_NAME = :name,
  COUNTRY_CODE  = :countryCode,
  UPDATE_DATE   = :lastUpdateDate
WHERE (COUNTRY_ID = :id)