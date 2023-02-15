package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * @description: ${table.comment!} 业务层
 * @author ${author}
 * @version: v1
 * @time ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
</#if>
