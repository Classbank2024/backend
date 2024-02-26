./gradlew bootjar

#export DATABASE_URL="__FILL_ME__"
#export DATABASE_USERNAME="__FILL_ME__"
#export DATABASE_PASSWORD="__FILL_ME__"
#export ENV="__FILL_ME__"
export DATABASE_URL="jdbc:mysql://ls-b069b43cd337b1735c40459ec950b01b689a7b14.cxg3q18enrr3.ap-northeast-2.rds.amazonaws.com:3306/our-class-bank";
export DATABASE_USERNAME="application";
export DATABASE_PASSWORD="chlqudgus01!";
export ENV="dev"

#mkdir log
#nohup ./run.sh > log/nohup.log 2>&1 < /dev/null &

java -jar -Dspring.profiles.active="$ENV" api/core-api/build/libs/core-api-0.0.1-SNAPSHOT.jar
