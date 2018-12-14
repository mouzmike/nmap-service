# nmap-service

After you have successfully cloned the repository, follow the steps below to run a containerized version of the app.

1. Enter the nmap-service directory.
2. sudo mvn clean install 
3. sudo docker build -t nmap-service --no-cache .
4. sudo docker run -it -p "7777:7777" nmap-service:latest
5. Initiate a sample scan with a command like *curl --header "Content-Type: application/json" --request POST --data '{"hosts": ["192.168.3.66"]}' http://localhost:7777/nmap* or *curl --header "Content-Type: application/json" --request POST --data '{"hosts": ["192.168.3.66"],"callbackURL": "https://app.olistic.io"}' http://localhost:7777/nmap* to add a callback once the job is done.