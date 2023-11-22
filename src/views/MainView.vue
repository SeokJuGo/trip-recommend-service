<script setup>
import paju from "../assets/img/파주프로방스.png";
import song from "../assets/img/송도한옥마을.jpg";
import eul from "../assets/img/을왕리.jpg";
</script>

<template>
    <!-- Main Content -->
    <div class="container-fluid bg-success-subtle">
        <div class="container-md">
            <!-- Carousel -->
            <div id="demo" class="carousel slide" data-bs-ride="carousel">
                <!-- Indicators/dots -->
                <div class="carousel-indicators">
                    <button
                        type="button"
                        data-bs-target="#demo"
                        data-bs-slide-to="0"
                        class="active"
                    ></button>
                    <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
                    <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
                </div>

                <!-- The slideshow/carousel -->
                <div class="carousel-inner" style="height: 70vh">
                    <div class="carousel-item h-100 active">
                        <img class="d-block h-100 ms-auto me-auto" :src="paju" alt="" />
                        <div class="carousel-caption">
                            <h5>경기도 파주 프로방스</h5>
                            <p>한국 속의 작은 유럽</p>
                        </div>
                    </div>
                    <div class="carousel-item h-100">
                        <img class="d-block h-100 ms-auto me-auto" :src="song" alt="" />
                        <div class="carousel-caption">
                            <h5>인천 송도 한옥마을</h5>
                            <p>도시 속 한옥</p>
                        </div>
                    </div>
                    <div class="carousel-item h-100">
                        <img class="d-block h-100 ms-auto me-auto" :src="eul" alt="" />
                        <div class="carousel-caption">
                            <h5>인천 을왕리 해수욕장</h5>
                            <p>서울 근교 백사장</p>
                        </div>
                    </div>
                </div>

                <!-- Left and right controls/icons -->
                <button
                    class="carousel-control-prev"
                    type="button"
                    data-bs-target="#demo"
                    data-bs-slide="prev"
                >
                    <span class="carousel-control-prev-icon"></span>
                </button>
                <button
                    class="carousel-control-next"
                    type="button"
                    data-bs-target="#demo"
                    data-bs-slide="next"
                >
                    <span class="carousel-control-next-icon"></span>
                </button>
            </div>
        </div>
    </div>

    <div class="container-fluid bg-primary-subtle">
        <div class="container p-5 text-light-emphasis">
            <h1>여행가고 싶을땐?</h1>
            <h3>
                "EnjoyTrip으로 지역 관광 명소를 발견하고, 손쉽게 여행을 계획하고, 가장 인기 있는
                목적지를 탐험하고, 귀중한 여행 통찰력을 공유하세요! 숨겨진 보석을 발견하고, 원활한
                여행 일정을 만들고, 전 세계를 여행하는 동료들과 연결하세요. 최고의 여행 동반자가
                여기 있으니 시작하세요. 오늘 우리와 함께 여행을 떠나고 모험을 시작해 보세요!"
            </h3>

            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                <div class="col">
                    <div class="card h-100">
                        <img src="../assets/img/한산도.jpg" class="card-img-top" alt="..." />
                        <div class="card-body">
                            <h5 class="card-title">통영 한산도</h5>
                            <p class="card-text">
                                자연의 고요함과 해안의 매력이 만나는 통영에서 한산도의 고요한
                                아름다움을 경험해보세요.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card h-100">
                        <img src="../assets/img/비양도.jpg" class="card-img-top" alt="..." />
                        <div class="card-body">
                            <h5 class="card-title">제주 비양도</h5>
                            <p class="card-text">
                                울창한 숲, 한적한 동굴, 깨끗한 해변, 숨막히는 해안 전망으로 장식된
                                자연의 원더랜드로 모험을 즐기는 사람들과 자연 애호가들이 꼭 방문해야
                                할 곳입니다.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card h-100">
                        <img src="../assets/img/고창.jpg" class="card-img-top" alt="..." />
                        <div class="card-body">
                            <h5 class="card-title">고창 선운사</h5>
                            <p class="card-text">
                                고창 선운사에서 최고의 고요함을 경험하세요. 깨끗한 산 속에 자리잡은
                                이 역사적인 보석은 내면의 평화와 문화 탐구를 추구하는 모든 사람에게
                                고요한 정원, 고대 건축물, 영적 휴양지를 제공합니다.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card h-100">
                        <img src="../assets/img/단양.jpg" class="card-img-top" alt="..." />
                        <div class="card-body">
                            <h5 class="card-title">단양 도담삼봉</h5>
                            <p class="card-text">
                                단양 도담삼봉의 경외심을 불러일으키는 아름다움을 발견하세요! 우뚝
                                솟은 세 개의 석회암 봉우리, 숨이 막힐 정도로 아름다운 자연 다리,
                                그리고 눈부신 강 풍경이 모험가와 사진작가 모두를 유혹하는 장엄한
                                풍경을 만들어냅니다.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card h-100">
                        <img src="../assets/img/문경.jpg" class="card-img-top" alt="..." />
                        <div class="card-body">
                            <h5 class="card-title">문경 문경새재</h5>
                            <p class="card-text">
                                문경새재의 매혹적인 매력을 문경에서 경험해보세요! 고대산길을 따라
                                역사의 발자취를 걷고, 울창한 숲을 탐험하며, 시간을 통한 문화여행에
                                빠져보세요.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card h-100">
                        <img src="../assets/img/고성.jpg" class="card-img-top" alt="..." />
                        <div class="card-body">
                            <h5 class="card-title">고성 해금강</h5>
                            <p class="card-text">
                                고성 해금강의 해안 경이로움을 느껴보세요! 수정처럼 맑은 바닷물에서
                                솟아오른 장엄한 바다 스택을 감상하고 숨겨진 동굴을 탐험하며 이 해변
                                보석을 정의하는 자연의 아름다움을 만끽하세요.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
