# pda-a-monitors #

[![Build Status](https://travis-ci.org/tasima-uniritter/pda-a-monitors.svg?branch=master)](https://travis-ci.org/tasima-uniritter/pda-a-monitors) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=br.edu.uniritter%3Amonitors&metric=coverage)](https://sonarcloud.io/dashboard?id=br.edu.uniritter%3Amonitors)

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
```
