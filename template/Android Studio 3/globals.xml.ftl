<?xml version="1.0"?>
<globals>

    <global id="activityClassSuper1" value="AppIntro" />
    <global id="appIntroFragment1" value="AppIntroFragment" />

    <global id="activityClassSuper2" value="AppIntro2" />
    <global id="appIntroFragment2" value="AppIntro2Fragment" />

    <global id="manifestOut" value="${manifestDir}" />
    <global id="srcOut" value="${srcDir}/${slashedPackageName(packageName)}" />
    <global id="resOut" value="${resDir}" />
    <global id="relativePackage" value="<#if relativePackage?has_content>${relativePackage}<#else>${packageName}</#if>" />
    <global id="generateKotlin" type="boolean"
            value="${(((includeKotlinSupport!false) || (language!'Java')?string == 'Kotlin')
                   && !(buildApi == 26 && (buildApiRevision!0) != 1))?string}" />

</globals>
