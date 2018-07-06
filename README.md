# pda-a-monitors #

[![Build Status](https://travis-ci.org/tasima-uniritter/pda-a-monitors.svg?branch=master)](https://travis-ci.org/tasima-uniritter/pda-a-monitors) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=br.edu.uniritter%3Amonitors&metric=coverage)](https://sonarcloud.io/dashboard?id=br.edu.uniritter%3Amonitors) [![Deployed on Heroku](https://img.shields.io/badge/%E2%86%91%20Deployed%20on-Heroku-7056bf.svg?style=flat-square)](https://pda-a-monitors.herokuapp.com/)

### How to  ###

Install gradle dependencies

Install Lombok plugin

Run docker file in root directory
``` ssh
  docker-compose up mq
```

Access  [http://localhost:15672/](http://localhost:15672/)
with credentials: admin / admin

Go in Exchanges and create 2 fanout exchanges: test and testout

Check income.connection in application.yml to set localhost queues

### .env file

This project use [.env](https://github.com/cdimascio/java-dotenv) file, create it on root project folder
with this content

```
AMQP_SERVICE_PASSWORD=******
JAVA_OPTS="-Xmx168m -Xms168m -XX:PermSize=32m -XX:MaxPermSize=32m -Xss1m -XX:+UseCompressedOops"
```

### Message pattern

The application expect an event like this:

```json
{
  "origin": "PC-0",
  "metric": "MEMORY_USAGE",
  "value": 500,
  "timestamp": 1530227658230
}
````

And send alerts like this:

```json
{
  "origin": "PC-0",
  "metric" : "MEMORY_USAGE",
  "value" : 500,
  "timestamp" : 1530227285565,
  "rule" : "GREATER_THAN",
  "threshold" : 250
}
```

Or, on timeout route:

```json
{
  "origin": "PC-0",
  "metric" : "MEMORY_USAGE",
  "value" : 51051,
  "timestamp" : 1530227336566,
  "rule" : "TIMEOUT",
  "threshold" : 5000
}
```

Deploy
------

Application is currently stopped, to start run

```
heroku ps:scale web=1
```
