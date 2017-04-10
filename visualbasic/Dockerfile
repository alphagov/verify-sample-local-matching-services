FROM mono:latest

ENV APPPATH /app

ENV PATH $APPPATH/bin:$PATH

RUN mkdir -p $APPPATH
COPY . $APPPATH
WORKDIR $APPPATH

RUN nuget restore
RUN xbuild

EXPOSE 8080

CMD [ "mono", "./bin/Debug/lms.exe" ]