package bg.softuni.defaultskelet.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import bg.softuni.contract.GarbageDisposalStrategy;
import bg.softuni.contract.StrategyHolder;

public class DefaultStrategyHolder implements StrategyHolder {
    private LinkedHashMap<Class<?>, GarbageDisposalStrategy> strategies;

    public DefaultStrategyHolder() {
        this.strategies = new LinkedHashMap<>();
    }

    @Override
    public Map<Class<?>, GarbageDisposalStrategy> getDisposalStrategies() {
        return Collections.unmodifiableMap(this.strategies);
    }

    @Override
    public boolean addStrategy(Class<?> annotationClass, GarbageDisposalStrategy strategy) {
        this.strategies.put(annotationClass, strategy);
        return true;
    }

    @Override
    public boolean removeStrategy(Class<?> annotationClass) {
        this.strategies.remove(annotationClass);
        return true;
    }
}
