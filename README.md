# DataExtract
Interview Assignment

# Software requirements
* Java(1.8)
* Spring(2.1.7.RELEASE)
* h2 database (Latest version)

# Project requirements
Database properties configured in application.properties

1. URL to fetch huge data from 3rd party api - https://api.mockaroo.com/api/85883510?count=5&key=61068170
Pull data in JSON format

2. URL (Does not work, only for testing) to push data - https://api.mockaroo.com/api/85883510?count=5&key=2020202
Push data in JSON format

NB: I have used api.mockaroo.com for testing and created a free account, which allows only 200 requests per day. If exceeded, PULL service method would result in 500 Internal Server Error