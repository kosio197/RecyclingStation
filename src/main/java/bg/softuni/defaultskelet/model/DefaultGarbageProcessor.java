package bg.softuni.defaultskelet.model;

import java.lang.annotation.Annotation;

import bg.softuni.annotation.Disposable;
import bg.softuni.contract.GarbageDisposalStrategy;
import bg.softuni.contract.GarbageProcessor;
import bg.softuni.contract.ProcessingData;
import bg.softuni.contract.StrategyHolder;
import bg.softuni.contract.Waste;

public class DefaultGarbageProcessor implements GarbageProcessor {

    private StrategyHolder strategyHolder;

    public DefaultGarbageProcessor(StrategyHolder strategyHolder) {
        this.setStrategyHolder(strategyHolder);
    }

    public DefaultGarbageProcessor() {
        this(new DefaultStrategyHolder());
    }

    private void setStrategyHolder(StrategyHolder strategyHolder) {
        this.strategyHolder = strategyHolder;
    }

    @Override
    public StrategyHolder getStrategyHolder() {
        return this.strategyHolder;
    }

    @Override
    public ProcessingData processWaste(Waste garbage) {
        Class<?> type = garbage.getClass();
        Annotation[] garbageAnnotations = type.getAnnotations();
        Class<?> disposableAnnotation = null;
        for (Annotation annotation : garbageAnnotations) {
            if (Disposable.class.isAnnotationPresent(annotation.annotationType())) {
                disposableAnnotation = annotation.annotationType();
                break;
            }
        }

        GarbageDisposalStrategy currentStrategy;
        if (disposableAnnotation == null
                || !this.getStrategyHolder().getDisposalStrategies().containsKey(disposableAnnotation)) {
            throw new IllegalArgumentException(
                    "The passed in garbage does not implement an annotation implementing the Disposable meta-annotation or is not supported by the StrategyHolder.");
        }

        currentStrategy = this.getStrategyHolder().getDisposalStrategies().get(disposableAnnotation);
        return currentStrategy.processGarbage(garbage);
    }
}
