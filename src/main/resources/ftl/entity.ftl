package ${packageName};

import java.util.Date;
//import com.pdsu.edu.domain.entity.${Paramss.Params0.classNameUpCase};

//通过mapKey.mapKey的方式获取设置的类名，因为每一个实体类中都有classNameUpCase
//这里取Params0这个key来用

public class ${classNameUpCase} {
//以下为循环实体类的代码
<#list Paramss?values as value>
    //${value.comment}
    private ${value.type} ${value.column};
</#list>
<#list Paramss?values as value>
    public ${value.type} get${value.columnNameUpCase}(){
    return ${value.column};
    }
    public void set${value.columnNameUpCase} (${value.type} ${value.column}s){
    this.${value.column} = ${value.column}s;
    }
</#list>
}