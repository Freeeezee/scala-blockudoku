FROM sbtscala/scala-sbt:amazoncorretto-al2023-17.0.13_1.10.7_3.6.2
LABEL authors="Mimi"

WORKDIR /blockudoku
ADD . /blockudoku
CMD sbt run


