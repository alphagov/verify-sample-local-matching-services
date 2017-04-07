FROM microsoft/dotnet

ENV APPPATH /app

ENV PATH $APPPATH/bin:$PATH

RUN mkdir -p $APPPATH
COPY . $APPPATH
WORKDIR $APPPATH

RUN dotnet restore

EXPOSE 5000

CMD [ "dotnet", "run" ]