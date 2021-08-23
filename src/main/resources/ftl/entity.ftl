package ${packageName};

import java.util.Date;

/**
* the code is generated , do't modify this code
* ${(classComment)!''}
*/
public class ${className} {

<#list fieldParams as value>
    /*${value.fileldComment}*/
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
