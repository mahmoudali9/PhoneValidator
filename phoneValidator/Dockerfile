FROM node:latest

WORKDIR /usr/src/app

COPY package.json .
COPY package-lock.json .

RUN npm i -g @angular/cli
RUN npm install

COPY . .

EXPOSE 4200

CMD ng serve --host 0.0.0.0 --disable-host-check