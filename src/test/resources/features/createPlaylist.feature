# language: ru
  Функция: Создание плейлиста

    Сценарий: Успешное создание плейлиста с названием my_playlist
      Когда Создать плейлист с названием 'my_playlist'
      Тогда Плейлист с названием 'my_playlist' отображается в плейлистах пользователся с id 4571342102