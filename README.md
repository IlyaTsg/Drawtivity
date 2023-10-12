# Drawtivity
**Запуск Backend'а**
1. Установить Docker
     - Linux(https://docs.docker.com/desktop/install/linux-install/)
     - Windows(https://docs.docker.com/desktop/install/windows-install/)
2. Скачать репозиторий `git clone git@github.com:IlyaTsg/Drawtivity.git`
3. В папке проекта запустить контейнер `docker-compose up`
4. По-умолчанию API работает на порту 8080. Порт можно поменять в файле docker-compose.yml(`- 8080:8080` -> `- нужный_порт:8080`)
