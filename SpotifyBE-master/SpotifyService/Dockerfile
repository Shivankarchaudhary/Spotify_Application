FROM openjdk
WORKDIR usr/lib
ENV MONGO_DATABASE="SpotifySong"
ENV MONGO_URL="mongodb://mongoService:27017/SpotifySong"

ADD ./target/SpotifyService-0.0.1-SNAPSHOT.jar /usr/lib/SpotifyService-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","SpotifyService-0.0.1-SNAPSHOT.jar"]