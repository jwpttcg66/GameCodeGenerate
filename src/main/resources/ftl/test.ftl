package ${packageName};

import java.util.Date;

/**
* the code is generated , do't modify this code
<#if classComment??>
* ${classComment}
</#if>
* @date ${.now?string('yyyy-MM-dd')}
*/
public class ${className} {

<#list fieldParams as value>
    <#if value.fileldComment??>
    /*${value.fileldComment}*/
    </#if>
    private ${value.filedType} ${value.filedName};
</#list>

<#list fieldParams as value>
    public ${value.filedType} get${value.filedName?cap_first}(){
    return this.${value.filedName};
    }

    public void set${value.filedName?cap_first}(${value.filedType} ${value.filedName}){
    this.${value.filedName} = ${value.filedName};
    }
</#list>
}
