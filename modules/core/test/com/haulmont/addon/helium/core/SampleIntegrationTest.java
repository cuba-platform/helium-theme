/*
 * Copyright (c) 2008-2020 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.addon.helium.core;

import com.haulmont.addon.helium.HeliumTestContainer;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.entity.User;
import org.junit.*;

import java.util.List;

public class SampleIntegrationTest {

    @ClassRule
    public static HeliumTestContainer cont = HeliumTestContainer.Common.INSTANCE;

    private static Metadata metadata;
    private static Persistence persistence;
    private static DataManager dataManager;

    @BeforeClass
    public static void setUp() throws Exception {
        metadata = cont.metadata();
        persistence = cont.persistence();
        dataManager = AppBeans.get(DataManager.class);
    }

    @AfterClass
    public static void tearDown() throws Exception {
    }

    @Test
    public void testLoadUser() {
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            TypedQuery<User> query = em.createQuery(
                    "select u from sec$User u where u.login = :userLogin", User.class);
            query.setParameter("userLogin", "admin");
            List<User> users = query.getResultList();
            tx.commit();
            Assert.assertEquals(1, users.size());
        }
    }
}