#language: ru
  @albums
  Функция: Добавление альбома в библиотеку
    Структура сценария: Успешное добавление альбома в библиотеку
      Когда Добавить альбом <albumName> в библиотеку пользователя '4571342102'
      Тогда Альбом <albumName> отображается в библиотеке пользователя '4571342102'

      Примеры:
        | albumName                            |
        | 'The Metallica Blacklist'            |
        | 'Discovery'                          |
        | 'Alive 2007'                         |
        | 'Homework'                           |
        | 'Watching Movies with the Sound Off' |
        | 'Swimming'                           |
        | 'IGOR'                               |
        | "I Ain't Got Time!"                  |
        | 'Florida Boy Do Your Dance!'         |
        | 'FIVE FIVE'                          |