/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2008-present Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.internal.commands;

import javax.inject.Inject;
import javax.inject.Named;

import org.sonatype.nexus.security.SecurityHelper;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.shiro.authc.UsernamePasswordToken;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An action to get a logged in user in the console.
 *
 * @since 3.3
 */
@Named
@Command(name = "login", scope = "nexus", description = "Put a user in context")
public class LoginAction
    implements Action
{
  private final SecurityHelper securityHelper;

  @Option(name = "-u", aliases = {"--username"}, description = "Username to login with", required = true)
  String username;

  @Option(name = "-p", aliases = {"--password"}, description = "Password to login with", required = true)
  String password;

  @Inject
  public LoginAction(final SecurityHelper securityHelper) {
    this.securityHelper = checkNotNull(securityHelper);
  }

  @Override
  public Object execute() throws Exception {
    securityHelper.subject().login(new UsernamePasswordToken(username, password));
    return null;
  }
}
