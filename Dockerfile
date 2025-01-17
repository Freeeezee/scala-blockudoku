FROM sbtscala/scala-sbt:eclipse-temurin-17.0.13_11_1.10.7_3.6.2

LABEL authors="Mimi"

RUN apt-get update
RUN apt-get install -y libgtk-3-0 libglib2.0-0 libxext6 libxrender1 libxtst6 libx11-6 xvfb

WORKDIR /blockudoku
ADD . /blockudoku
CMD ["sbt", "run"]


