# pda-a-monitors #
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

Check income.connection in application.properties to set localhost queues
