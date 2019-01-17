create schema gasStations;
create table gasStations.prices (
  placeId          int primary key,
  regularPrice     decimal,
  regularUpdatedAt timestamp,
  premiumPrice     decimal,
  premiumUpdatedAt timestamp,
  dieselPrice      decimal,
  dieselUpdatedAt  timestamp
);