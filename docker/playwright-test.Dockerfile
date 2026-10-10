FROM mcr.microsoft.com/playwright/java:v1.61.0-noble

USER root

RUN apt-get update \
    && apt-get install -y --no-install-recommends \
        python3 \
        curl \
    && rm -rf /var/lib/apt/lists/*
