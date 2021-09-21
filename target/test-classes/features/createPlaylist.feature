# language: ru
  Функция: Создание плейлиста
    @playlist
    Структура сценария: Успешное создание плейлиста с названием Create Playlist Test
      Когда Создать плейлист с названием <playlistName>
      Тогда Плейлист с названием <playlistName> отображается в плейлистах пользователся с id 4571342102

      Примеры:
        | playlistName                                         |
        | 'Create Playlist Test'                               |
        | '123456789!@#$%^*()_+-='                             |
        | '50qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklz50' |
