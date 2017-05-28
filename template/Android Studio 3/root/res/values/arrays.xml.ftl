<?xml version="1.0" encoding="utf-8"?>
<resources>

    <!-- Application introduction slides -->
    <string-array name="intro_title">
        <#list 1..nbSlides?number as i>
        <item>Title slide ${i}</item>
        </#list>
    </string-array>
    <string-array name="intro_description">
        <#list 1..nbSlides?number as i>
        <item>Description of the slide ${i}</item>
        </#list>
    </string-array>

</resources>
