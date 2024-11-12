<template>
    <div>
      <component :is="currentStep" @next="nextStep" @previous="previousStep" />
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  import Step1 from './Step1.vue';
  import Step2 from './Step2.vue';
  
  export default {
    components: { Step1, Step2 },
    setup() {
      const steps = [Step1, Step2];
      const currentStepIndex = ref(0);
  
      const nextStep = () => {
        if (currentStepIndex.value < steps.length - 1) {
          currentStepIndex.value += 1;
        }
      };
      
      const previousStep = () => {
        if (currentStepIndex.value > 0) {
          currentStepIndex.value -= 1;
        }
      };
  
      return {
        steps,
        currentStepIndex,
        nextStep,
        previousStep,
        currentStep: computed(() => steps[currentStepIndex.value]),
      };
    },
  };
  </script>
  