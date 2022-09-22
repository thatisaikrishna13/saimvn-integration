package org.apache.maven.it;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.shared.verifier.util.ResourceExtractor;
import org.apache.maven.shared.verifier.Verifier;

import java.io.File;

/**
 *
 * @author Benjamin Bentmann
 *
 */
public class MavenIT0133JarLifecycleTest
    extends AbstractMavenIntegrationTestCase
{

    public MavenIT0133JarLifecycleTest()
    {
        super( "[2.0.0,)" );
    }

    /**
     * Test default binding of goals for "jar" lifecycle.
     *
     * @throws Exception in case of failure
     */
    public void testit0133()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/it0133" );

        Verifier verifier = newVerifier( testDir.getAbsolutePath() );
        verifier.deleteDirectory( "target" );
        verifier.setAutoclean( false );
        verifier.executeGoal( "deploy" );
        verifier.verifyFilePresent( "target/resources-resources.txt" );
        verifier.verifyFilePresent( "target/compiler-compile.txt" );
        verifier.verifyFilePresent( "target/resources-test-resources.txt" );
        verifier.verifyFilePresent( "target/compiler-test-compile.txt" );
        verifier.verifyFilePresent( "target/surefire-test.txt" );
        verifier.verifyFilePresent( "target/jar-jar.txt" );
        verifier.verifyFilePresent( "target/install-install.txt" );
        verifier.verifyFilePresent( "target/deploy-deploy.txt" );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();
    }

}
