FROM ubuntu:latest
LABEL authors="atropos"

ENTRYPOINT ["top", "-b"]