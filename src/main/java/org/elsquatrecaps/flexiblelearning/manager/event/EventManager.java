/*
 * Copyright 2021 Grup de millora MetFlex.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.elsquatrecaps.flexiblelearning.manager.event;

import java.util.Map;
import org.elsquatrecaps.flexiblelearning.manager.event.responses.DataEventResponse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author josep
 */
public interface EventManager <LS, PMLS extends PagingAndSortingRepository<LS, String>&QueryByExampleExecutor<LS>, 
        LP, PMLP extends PagingAndSortingRepository<LP, String>&QueryByExampleExecutor<LP>, A, PMA extends PagingAndSortingRepository<A, String>&QueryByExampleExecutor<A>> {

    void init(PMLS learningStateRepository, PMLP learningProposalRepository, PMA  activityRepository);
    ModelAndView processEventAndResponseHtml(Map<String,String> eventData);
    DataEventResponse processEventAndResponseJson(Map<String,String> eventData);
}
