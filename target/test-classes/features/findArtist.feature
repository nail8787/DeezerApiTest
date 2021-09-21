# language:ru
Функция: Поиск информации об исполнителе

  @search @artist
  Структура сценария: Успешный поиск исполнителя
    Когда Найти исполнителя с именем <artistName>
    Тогда В ответе корректный уникальный идентификатор артиста <artistName>

    Примеры:
      | artistName    |
      | 'The Beatles' |
      | 'Rammstein'   |
      | 'Drake'       |
      | 'Daft Punk'   |