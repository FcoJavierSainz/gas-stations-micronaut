#!/bin/bash
mongoimport --host mongodb --db gasStations --collection places --type json --file /init.json --jsonArray
mongo gasStations --host mongodb --eval 'db.places.createIndex( { location: "2dsphere" } )'