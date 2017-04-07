FROM mono:latest

ENV APPPATH /app

ENV PATH $APPPATH/bin:$PATH

RUN mkdir -p $APPPATH
COPY . $APPPATH
WORKDIR $APPPATH

RUN nuget restore
RUN xbuild

EXPOSE 9991

CMD [ "mono", "./bin/Debug/lms.exe" ]