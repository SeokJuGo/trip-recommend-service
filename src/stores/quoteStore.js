import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useQuoteStore = defineStore('quote', () => {
    // const count = ref(0)
    // const name = ref('Eduardo')
    // const doubleCount = computed(() => count.value * 2)
    // function increment() {
    //   count.value++
    // }
    // return { count, name, doubleCount, increment }
    
    const quotes = ref([
        { text: "여행은 삶을 더 크게 만든다.", author: "알렉산더 수퍼트" },
        { text: "여행은 언제나 새로운 시작이다.", author: "안소니 보덴" },
        { text: "여행은 당신을 발견하는 것이다.", author: "청년이탈리아" },
        { text: "여행은 마음을 열고 눈을 뜨게 만든다.", author: "잭 케랙" },
        { text: "모험이 없는 여행은 새로운 경험이 아니다.", author: "알 프랭크" },
        { text: "여행은 마음을 키우고 영혼을 품는 것이다.", author: "그레이시 홉번" },
        { text: "당신이 길을 가다 보면, 길이 당신을 찾아온다.", author: "작자 미상" },
        { text: "세계를 보는 것은 자기 자신을 보는 것이다.", author: "헤리 에이미슨" },
        { text: "세상 어디든 당신이 가는 곳, 그곳이 여행지다.", author: "도로시 길먼" },
        { text: "여행은 사랑하는 사람들과 함께하는 것이다.", author: "비언드 케르쉬너" },
        { text: "새로운 길은 언제나 새로운 모험의 시작이다.", author: "로버트 프로스트" },
        { text: "모든 여행은 자기 자신을 발견하는 여정이다.", author: "제임스 매튜 바리" },
        { text: "당신이 가는 곳마다, 당신은 그곳의 일부가 된다.", author: "앤서니 부르덴" },
        { text: "여행은 살아보는 거야, 공부하는 게 아니야.", author: "미르치아 엘리아데스" },
        { text: "세계는 책을 읽는 사람에게는 작은 마을이다.", author: "알렉산더 솔레제니친" },
        { text: "세계 각지의 풍경은 우리가 쓰는 시에 영감을 준다.", author: "마하트마 간디" },
        { text: "세상을 보는 것은 책을 읽는 것보다 더 나은 교육이다.", author: "제임스 버먼" },
        { text: "여행은 우리의 눈을 세상의 아름다움으로 열어준다.", author: "랄프 왈도 에머슨" },
        { text: "여행은 당신을 변화시킨다. 당신은 항상 어떤식으로든지.", author: "안토니 로렌" },
        { text: "여행은 당신이 모르는 것을 알게 하는 가장 좋은 방법이다.", author: "마크 트웨인" },
    ]);

    function length() {
        return quotes.value.length;
    };

    function getRandomQuote() {
        const randomIndex = Math.floor(Math.random() * quotes.value.length);
        return quotes.value[randomIndex];
    };

    return { quotes, length, getRandomQuote };
});