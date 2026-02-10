package Analysis;

import Internals.Errors.ErrorController;
import Internals.Errors.ErrorEnum;
import Internals.FlagEnum;
import Internals.FlagState;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static Internals.Errors.ErrorEnum.LOGICA_CONTROL_NO_EXHAUSTIVA;

public class FlagExhaustivenessHelper {

    public static void main(String[] args) {
        var err = new ErrorController();
        var helper = new FlagExhaustivenessHelper(err);
        helper.addNewFlagCombination(Set.of(new FlagState(FlagEnum.F,true)));
        helper.addNewFlagCombination(Set.of(new FlagState(FlagEnum.F,false)));

        helper.check();
    }

    public FlagExhaustivenessHelper(ErrorController err){
        this.err = err;
    }

    private ErrorController err = null;
    private  Set<FlagEnum> flagsObserved = new LinkedHashSet<>();
    private  List<Set<FlagState>> flagStatesWritten = new ArrayList<>();

    public void addNewFlagCombination(Set<FlagState> flagStates){
            flagStates.forEach(fs -> {
                var repeated = !flagsObserved.add(fs.getFlag());

            });
            flagStatesWritten.add(flagStates);
    }

    public FlagExhaustivenessError check(){

        var flagsStatesWrittenFull = flagStatesWritten.stream()
                .map(fs -> toBinary(fs, flagsObserved))
                .flatMap(this::eliminateWildCarts)
                .toList();

        var repeatedElementsWritten = flagsStatesWrittenFull.stream()
                .filter(e -> Collections.frequency(flagsStatesWrittenFull,e) > 1)
                .toList();

        if(!repeatedElementsWritten.isEmpty()){
            return new RepeatedConditionError(repeatedElementsWritten.stream()
                    .map(c ->toSet(c,flagsObserved))
                    .distinct()
                    .map(s -> {return s.stream()
                            .map(FlagState::getInputName)
                            .collect(Collectors.joining(" "));
                    })
                    .toList()
            );

        }

        //  Create flagStatesIdeal based on flagsObserved
        var flagStatesIdeal = allFlagCombinations(flagsObserved);
        // Get flagStatesDifference from flagStatesIdeal and flagStatesWritten
        var flagStateDifference = flagStatesIdeal.stream()
                .filter(idealString -> !flagsStatesWrittenFull.contains(idealString))
                .toList();

        if(!flagStateDifference.isEmpty()){
            return new MissingConditionError(
                flagStateDifference.stream()
                        .map(c -> toSet(c,flagsObserved))
                        .map(s -> {return s.stream()
                                .map(FlagState::getInputName)
                                .collect(Collectors.joining(" "));
                        })
                        .toList()
            );
        }

        //Borramos los estados anteriores
        flagsObserved = new LinkedHashSet<>();
        flagStatesWritten = new ArrayList<>();
        return new NoError();
    }


    private String toBinary(Set<FlagState> flagStates, Set<FlagEnum> flagsObserved){

        return flagsObserved.stream().map(fo ->{
            var state = flagStates.stream()
                    .filter(fs ->fs.getFlag() == fo)
                    .map(FlagState::getState)
                    .findFirst();

            if (state.isEmpty()){
                return  "-";
            }else if(state.get()){
                 return  "1";
            }else {
                return "0";
            }
        }).collect(Collectors.joining(""));

    }

    private Set<FlagState> toSet(String combination, Set<FlagEnum> flags){
        assert combination.length() == flags.size();
        var flagArray = new ArrayList<>(flags);
        var res = new LinkedHashSet<FlagState>();
        for (int i = 0; i < flagArray.size(); i++) {
            var flag = flagArray.get(i);
            Boolean state = null;
            state = combination.charAt(i) == '1';
            res.add(new FlagState(flag,state));
        }
        return res;
    }



    private Stream<String> eliminateWildCarts(String binary){
        List<String> res = List.of(binary);
        while(res.stream().anyMatch(s -> s.contains("-"))){
            res = res.stream()
                    .flatMap(s -> {
                        if (s.contains("-")) {
                            return Stream.of(
                                    s.replaceFirst("-", "0"),
                                    s.replaceFirst("-", "1")
                            );
                        } else {
                            return Stream.of(s);
                        }
                    }).toList();
        }
        return res.stream();
    }
    private List<String> allFlagCombinations(Set<FlagEnum> flagsObserved){
        var flagNumber = flagsObserved.size();
        var numLimit =(int) Math.pow(2,flagNumber);

        return IntStream.range(0,numLimit)
            .mapToObj(Integer::toBinaryString)
            .map(c ->{
                return String.format("%"+ flagNumber + "s",c).replace(" ","0");
            })
            .toList();
    }

    public sealed interface FlagExhaustivenessError permits
            RepeatedConditionError,
            MissingConditionError,
            NoError{
        public List<String> getConditions();
    }

    public record RepeatedConditionError(List<String> conditions) implements FlagExhaustivenessError{
        @Override
        public List<String> getConditions(){
            return conditions;
        }
    }
    public record MissingConditionError(List<String> conditions) implements FlagExhaustivenessError{
        @Override
        public List<String> getConditions(){
            return conditions;
        }
    }
    public record NoError() implements FlagExhaustivenessError{
        @Override
        public List<String> getConditions(){
            return Collections.emptyList();
        }
    }
}

