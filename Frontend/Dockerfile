# -------- BUILD --------
FROM node:18 AS build
WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build --prod

# -------- RUN --------
FROM nginx:alpine
COPY --from=build /app/dist/FRONTEND /usr/share/nginx/html
EXPOSE 80
