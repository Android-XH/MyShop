package shop.service.impl;

import org.springframework.stereotype.Service;
import shop.mode.Config;
import shop.mode.ConfigExample;
import shop.mode.mapper.ConfigMapper;
import shop.service.ConfigService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigServiceImpl extends BaseServiceImpl<ConfigMapper, ConfigExample> implements ConfigService {
    @Override
    public Map<String, String> map() throws Exception{
        List<Config> configs = list();
        Map<String,String> configMap = new HashMap<>();
        for(Config config:configs){
            configMap.put(config.getName(),config.getValue());
        }
        return configMap;
    }
}