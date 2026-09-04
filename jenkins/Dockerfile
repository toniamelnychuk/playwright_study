FROM jenkins/jenkins:lts-jdk21

USER root

# Install Maven and tools needed for Playwright browsers
RUN apt-get update \
    && apt-get install -y \
        maven \
        wget \
        curl \
        unzip \
        libnss3 \
        libatk-bridge2.0-0 \
        libdrm2 \
        libxkbcommon0 \
        libxcomposite1 \
        libxdamage1 \
        libxfixes3 \
        libxrandr2 \
        libgbm1 \
        libasound2 \
        libatspi2.0-0 \
        libgtk-3-0 \
        libpango-1.0-0 \
        libcairo2 \
    && rm -rf /var/lib/apt/lists/*

USER jenkins