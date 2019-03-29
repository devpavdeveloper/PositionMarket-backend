docker-compose -f build.yml down -v --rmi all
docker-compose -f build.yml down --remove-orphans
