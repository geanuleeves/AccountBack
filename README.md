## 前后端分离 Spring Booot + Vue 开发单页面应用（一）

毕业设计：个人理财
项目后端系统

### 开发环境介绍
* JDK1.8
* Node v8.9.3
* npm v5.5.1
* 开发工具IDEA（安装Vue.js插件）
* 数据库MySQL 57
* 版本管理工具 Git

### Redis缓存（使用方法案例）
使用缓存
保存的时候使用 @Cacheable，清空使用 @CacheEvict ，更新的时候使用 @CachePut 。

反序列化有bug，没有实现 Serializable 的只能序列化，无法反序列化。可能后续版本会解决该问题。

所以把下面的查询代码修改一下，用 实现了 Serializable 的 ArrayList 包装返回。

@Cacheable("config")
@Override
public Collection<Config> getAll() {
  System.out.println("\n----------GetAll----------\n");
  return new ArrayList<>(configs.values());
}

@CacheEvict(value = CacheNames.CONFIG, allEntries = true)
@Override
public long add(Config config) {

}

/**
 * 删除配置项
 */
@CacheEvict(value = CacheNames.CONFIG, allEntries = true)
@Override
public boolean delete(long id) {
  
}
定时清空缓存
也可以定时清空cache，使用 @EnableScheduling 和 @Scheduled 注解。

@Component
@EnableScheduling
public class ClearCacheTask {

  /**
   * 定时清空缓存
   */
  @Scheduled(fixedRate = 60 * 1000L)
  @CacheEvict(value = { CacheNames.CONFIG }, allEntries = true)
  public void clearCaches() {
    System.out.println("\n------------ clear caches ------------\n");
  }
}
redis 怎么样保存cache
增加2条数据，一个是类型为 zset 的 缓存名~keys , 里面存放了该缓存所有的key， 一个是对应的key，值为序列化后的json。

zset 是带权重的有序集合，可以使用 zrange config~keys -1 1 withscores 查看元素，新加入的都是 0.0 。使用 zcount config~keys -1 1 查看个数。

可以使用 ttl 命令查看超时时间，单位为秒。

