# Bank API
Банковское приложение

## Модель
Пользователь может владеть 1 или несколькими банковскими аккаунтами. 
К 1 аккаунту может быть привязано несколько банковских карт.

## Сервис
Сервис с бизнес-логикой работы с аккаунтами
Сервис с бизнес-логикой работы с картами

## БД
Данные хранятся в БД
БД в отдельном докер-контейнере
Взаимодействие с БД реализовано через spring data jpa

## REST
Взаимодействие с сервисом происходит по REST, через контроллеры.

## Разворачивание приложение и деплой
Приложение разворачивается на localhost или в Openshift кластере.

## Security
Подключена spring security 
Каждый пользователь может видеть только свои аккаунты и карты после авторизации.
Пользователь админ может видеть данные по аккаунтам и картам всех пользователей.