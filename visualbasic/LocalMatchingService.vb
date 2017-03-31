Imports System.Net
Imports System.Io
Imports System.Json
Imports Microsoft.VisualBasic

Public Class LocalMatchingService

  Public Shared Function HandleMatching(ByRef request As HttpListenerRequest) As String
    If request.HasEntityBody Then
      Dim json As JsonObject = JsonObject.Load(request.InputStream)
      Dim jsonValue As JsonObject
      If json.TryGetValue("matchingDataset", jsonValue) Then
        Dim firstSurname As JsonObject = jsonValue("surnames")(0)
        If firstSurname("value").ToString() Like """Griffin""" Then
          Return "match"
        End If
      End If
      If json.TryGetValue("cycle3Dataset", jsonValue) Then
        If jsonValue IsNot Nothing Then
          Dim cycle3Attribute As JsonObject = jsonValue("attributes")
          If cycle3Attribute("nino").ToString() Like """goodvalue""" Then
            Return "match"
          End If         
        End If
      End If
    End If
    Return "no-match"
  End Function
  
  Public Shared Function HandleAccountCreation(ByRef request As HttpListenerRequest) As String
    If request.HasEntityBody Then
      Dim json As JsonObject = JsonObject.Load(request.InputStream)
      Dim jsonValue As JsonObject
      If json.TryGetValue("levelOfAssurance", jsonValue) Then
        If jsonValue.ToString() Like """LEVEL_2""" Then
          Return "success"
        End If
      End If
    End If
    Return "failure"
  End Function
  
  Public Shared Sub GenerateJsonResponse(ByVal value As String, ByRef response As HttpListenerResponse)
    Dim jsonValue As JsonValue = value
    response.ContentType = "application/json"
    Dim jsonResponse As JsonObject = New JsonObject()
    jsonResponse.Add("result", value)
    response.ContentLength64 = Strings.Len(jsonResponse.ToString())
    Using streamWriter As StreamWriter = New StreamWriter(response.OutputStream)
      streamWriter.Write(jsonResponse.ToString())
    End Using
    response.OutputStream.Close()
    response.Close()
  End Sub

  Public Shared Sub Main()
    Dim serverLocation As String = "http://+:8080/"
    Dim httpListener As HttpListener = New HttpListener()
    httpListener.Prefixes.Add(serverLocation)
    httpListener.Start()
    System.Console.WriteLine("Starting local matching service")
    System.Console.WriteLine("Listening at "+serverLocation+"...")
    
    Do
       Dim context As HttpListenerContext = httpListener.GetContext()
       System.Console.WriteLine(System.DateTime.Now+": "+context.Request.HttpMethod+ " "+context.Request.Url.ToString+" content-type: "+context.Request.ContentType)
       context.Response.SendChunked = False
       If context.Request.HttpMethod Like "POST" Then
        If context.Request.Url.LocalPath Like "/visualbasic/matching-service" Then
          Dim returnValue As String = HandleMatching(context.Request)
          GenerateJsonResponse(returnValue, context.Response)
          Continue Do
        End If
        If context.Request.Url.LocalPath Like "/visualbasic/account-creation" Then
          Dim returnValue As String = HandleAccountCreation(context.Request)
          GenerateJsonResponse(returnValue, context.Response)
          Continue Do
        End If     
       End If
       context.Response.StatusCode = 404
       context.Response.Close()
    Loop    
  End Sub
End Class

