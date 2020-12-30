<html>
    <head>
       <title>Code</title>
       <link rel="stylesheet"
             href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/10.5.0/styles/default.min.css">
       <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/10.5.0/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>
   </head>
    <body>

        <#assign aDateTime = code.date>
        <span id="load_date">${aDateTime?datetime.iso?string("yyyy/MM/dd HH:mm:ss")}</span>

        <#if code.views == 0 && code.time == 0 && code.baseViews == 0>
        <#elseif code.views == 0 && code.baseViews != 0>
            <span id="views_restriction">${code.views} more views allowed</span>
        <#elseif code.views != 0 && code.time != 0>
            <span id="views_restriction">${code.views} more views allowed</span>
            <span id="time_restriction">The code will be available for ${code.time} seconds</span>
        <#elseif code.views != 0 && code.time == 0>
            <span id="views_restriction">${code.views} more views allowed</span>
        <#elseif code.views == 0 && code.time != 0>
            <span id="time_restriction">The code will be available for ${code.time} seconds</span>
        </#if>

        <pre id="code_snippet">
            <code class="language-java">
                ${code.code}
            </code>
        </pre>
    </body>
</html>