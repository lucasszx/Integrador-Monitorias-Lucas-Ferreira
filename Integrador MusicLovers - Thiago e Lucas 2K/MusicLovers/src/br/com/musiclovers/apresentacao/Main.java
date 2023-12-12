package br.com.musiclovers.apresentacao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import br.com.musiclovers.entidades.Playlist;
import br.com.musiclovers.entidades.Usuario;
import br.com.musiclovers.persistencia.ConexaoMySQL;
import br.com.musiclovers.persistencia.PlaylistDAO;
import br.com.musiclovers.persistencia.UsuarioDAO;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		ConexaoMySQL conexao = new ConexaoMySQL();
		conexao.setNomeBD("bd_musics");
		conexao.setSenha("gifdomysql3241#");
		System.out.println("\033[1;93m  ___                  _         _     _ \r\n"
				+ " | _ ) ___ _ __   __ _(_)_ _  __| |___| |\r\n"
				+ " | _ \\/ -_) '  \\  \\ V / | ' \\/ _` / _ \\_|\r\n"
				+ " |___/\\___|_|_|_|  \\_/|_|_||_\\__,_\\___(_)\r\n"
				+ "                                         ");
		Thread.sleep(750);
		while (true) {
			
			System.out.println("\033[1;93mSeja bem vindo ao MusicLovers♥, o que desejas?\n"
					+ "1. Realizar Login\n2. Criar nova conta\n3. Fechar programa\033[0;34m");
			String selection = sc.nextLine();
			if (selection.equals("1")) {
				UsuarioDAO udao = new UsuarioDAO(conexao);
				List<Usuario> listUsers = new ArrayList<Usuario>(udao.buscarTodos());
				System.out.print("\033[1;35m█ ");
				for (int i = 0; listUsers.size() > i; i++) {
					System.out.print(listUsers.get(i).getNome() + " █ ");
				}
				System.out.println("\033[0;33m\nDigite seu nome de acesso:\033[0;32m");
				Usuario user = null;
				while (true) {
					String nomeUser = sc.nextLine();
					user = udao.buscarPorNome(nomeUser);
					if (user != null) {
						System.out.println("\033[0;33mPor favor digite sua senha, " + user.getNome() + "!\033[0;32m");
						break;
					} else {
						System.out.println("\033[0;33mUsuário inexistente! Digite um usuário válido segundo os acima apresentados:");
					}
				}
				long idUser = user.getIdUsuario();
				while(true) {
					String senha = sc.nextLine();
					if (!senha.equals(user.getSenha())) {
						System.out.println("\033[0;33mSenha incorreta! Digite novamente ou reinicie o programa.");
						continue;
					} else {
						break;
					}
				}
				while(true) {
					System.out.println("\033[0;101mA c e s s o : concedido\n\n");
					Thread.sleep(500);
					System.out.println("   \\  |              _)        |                                   \r\n"
							+ "  |\\/ |  |   |   __|  |   __|  |       _ \\ \\ \\   /  _ \\   __|  __| \r\n"
							+ "  |   |  |   | \\__ \\  |  (     |      (   | \\ \\ /   __/  |   \\__ \\ \r\n"
							+ " _|  _| \\__,_| ____/ _| \\___| _____| \\___/   \\_/  \\___| _|   ____/ \r\n"
							+ "                                                                   \033[0m\n");
					Thread.sleep(400);
					while(true) {
						System.out.println("\033[1;37m\nSelecione o que deseja!\n1. Tocar musica Wav\n2."
								+ " Playlists\n3. Configurações de usuário\n4. Encerrar o programa/Logout");
						String menu = sc.nextLine();
						boolean deletou = false;
						if (menu.equals("1")) {
							while(true) {
								System.out.println("\033[0;31mPlayer. \n1. Digite o local exato da música.wav\n2. Digite 2 para voltar");
								String music = sc.nextLine();
								if (music.equals("2")) {
									break;
								}
								try {
						            File file = new File(music);
						            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
						            Clip clip = AudioSystem.getClip();
						            clip.open(audioStream);
						            clip.start();
						            while (!clip.isRunning()) {
						                Thread.sleep(10);
						            }
						            while (clip.isRunning()) {
						                Thread.sleep(10);
						            }
						            clip.stop();
						            Thread.sleep(1000);
						            clip.start();

						            clip.close();
						            audioStream.close();
						        } catch (Exception e) {
						            System.out.println("Erro ao reproduzir música: " + e.getMessage());
						        }
							}
						} else if (menu.equals("2")) {
							PlaylistDAO pdao = new PlaylistDAO (conexao);
							List<Playlist> plist = null;
							while(true) {
								plist = pdao.buscarTodosDoUsuario(user.getIdUsuario());
								if (plist.size() != 0) {
									System.out.print("\033[0;32m\n█ ");
									for (int i = 0; plist.size() > i; i++) {
										System.out.print(plist.get(i).getNome() + " █ ");
									}
									System.out.println("\n\nEssas são suas playlists\n1. Criar\n2. Editar\n3. Excluir\n4. Sair");
									String pmenu = sc.nextLine();
									if (pmenu.equals("1")) {
										System.out.println("\nDigite o nome da Playlist (Não pode haver nomes iguais):");
										String nomepl = sc.nextLine();
										Playlist pl = new Playlist(); 
										pl.setNome(nomepl);
										pl.setUsuario(user);
										pdao.adicionar(pl);
										System.out.println("\nFeito!\n");
									} else if (pmenu.equals("2")) {
										System.out.println("\nDigite o nome da Playlist a editar:");
										String nomepl = sc.nextLine();
										Playlist pl = new Playlist(); 
										pl.setIdPlaylist(pdao.buscarPorNome(nomepl).getIdPlaylist());
										System.out.println("\nDigite o novo nome:");
										String nomepl2 = sc.nextLine();
										pl.setNome(nomepl2);
										pl.setUsuario(user);
										pdao.editar(pl);
										System.out.println("\nFeito!\n");
									} else if (pmenu.equals("3")) {
										System.out.println("\nDigite o nome da Playlist a excluir:");
										String nomepl = sc.nextLine();
										Playlist pl = new Playlist(); 
										pl.setIdPlaylist(pdao.buscarPorNome(nomepl).getIdPlaylist());
										pl.setNome(nomepl);
										pl.setUsuario(user);
										pdao.excluir(pl);
										System.out.println("\nFeito!\n");
									} else if (pmenu.equals("4")) {
										break;
									} else {
										System.out.println("\nOpção inválida\n");
									}
								} else {
									System.out.println("\nVocê não possui nenhuma Playlist, deseja criar alguma?\n1. Sim\n2. Sair");
									String criar = sc.nextLine();
									if (criar.equals("1")) {
										System.out.println("\nDigite o nome da Playlist:");
										String nomepl = sc.nextLine();
										Playlist pl = new Playlist(); 
										pl.setNome(nomepl);
										pl.setUsuario(user);
										pdao.adicionar(pl);
										System.out.println("\nFeito!\n");
									} else {
										break;
									}
								}
							}
							
						} else if (menu.equals("3")) {
							while(true) {
								System.out.println("\033[1;36m\n ____ configurações ___\n1. Editar nome e/ou senha\n2."
										+ " Deletar a conta\n3. Voltar");
								String configUser = sc.nextLine();
								if (configUser.equals("1")) {
									System.out.println("\nDigite o nome desejado:");
									String newName;
									while(true) {
										newName = sc.nextLine();
										if (!newName.equals(user.getNome())) {
											user = null;
											user = udao.buscarPorNome(newName);
											if (user != null) {
												System.out.println("Usuário existente! Digite um nome novo:");
												continue;
											} else {
												break;
											}
										} else {
											break;
										}
									}
									System.out.println("Crie uma senha com no mínimo 8 digitos:");
									String newSenha;
									while(true) {
										newSenha = sc.nextLine();
										if (newSenha.length() > 7 && newSenha.length() < 20) {
											break;
										} else {
											System.out.println("A senha tem que ter 8 a 20 digitos. Repita o processo:");
										}
									}
									user.setIdUsuario(idUser);
									user.setNome(newName);
									user.setSenha(newSenha);
									udao.editar(user);
									System.out.println("Feito.\n");
								} else if (configUser.equals("2")) {
									System.out.println("\nDeseja mesmo excluir o seu usuário?\nYes | No");
									String delete = sc.nextLine();
									if (delete.toLowerCase().equals("yes")) {
										PlaylistDAO pdao = new PlaylistDAO(conexao);
										pdao.excluirTodasPlaylists(user);
										udao.excluir(user);
										deletou = true;
										break;
									}
								} else if (configUser.equals("3")) {
									break;
								} else {
									System.out.println("Opção inválida.");
								}
							}
						} else if (menu.equals("4")) {
							break;
						} else {
							System.out.println("Opção inválida.");
						}
						
						if (deletou) {
							break;
						}
					}
					break;
				}
				break;

			} else {
				if (selection.equals("2")) {
					UsuarioDAO udao = new UsuarioDAO(conexao);
					List<Usuario> listUsers = new ArrayList<Usuario>(udao.buscarTodos());
					String nomes[] = new String[listUsers.size()];
					for (int y = 0; listUsers.size() > y; y++) {
						nomes[y] = listUsers.get(y).getNome();
					}
					System.out.println("Crie um nome de acesso:");
					String nome = "";
					
					while(true) {
						nome = sc.nextLine();
						
						if (nome.length() == 0) {
							System.out.println("O nome não pode estar vazio. Digite novamente:");
						} else {
							boolean pass = true;
							for (int z = 0; nomes.length > z; z++) {
								if (nomes[z].equalsIgnoreCase(nome)) {
									System.out.println("O nome não pode ser igual a de outro usuário. Digite novamente:");
									pass = false;
									break;
								}
							}
							if (pass) {
								break;
							}
						}
					}
					System.out.println("Crie uma senha com no mínimo 8 digitos:");
					String senha;
					while(true) {
						senha = sc.nextLine();
						if (senha.length() > 7 && senha.length() < 20) {
							break;
						} else {
							System.out.println("A senha tem que ter 8 a 20 digitos. Repita o processo:");
						}
					}
					Usuario user = new Usuario();
					user.setNome(nome);
					user.setSenha(senha);
					UsuarioDAO udao2 = new UsuarioDAO(conexao);
					if (udao2.adicionar(user) != 0) {
						System.out.println("\n\033[1;32mCadastro bem sucedido!");
					} else {
						System.out.println("Servidor com mal funcionamento, tente novamente mais tarde.");
						break;
					}
				} else {
					if (selection.equals("3")) {
						break;
					}
				}
			}
			
			
		}
		System.out.println("\033[0;31m\nPrograma encerrado!\n");
		Thread.sleep(400);
		System.out.println("• ▌ ▄ ·. ▄• ▄▌.▄▄ · ▪   ▄▄· ▄▄▌         ▌ ▐·▄▄▄ .▄▄▄  .▄▄ · \r\n"
				+ "·██ ▐███▪█▪██▌▐█ ▀. ██ ▐█ ▌▪██•  ▪     ▪█·█▌▀▄.▀·▀▄ █·▐█ ▀. \r\n"
				+ "▐█ ▌▐▌▐█·█▌▐█▌▄▀▀▀█▄▐█·██ ▄▄██▪   ▄█▀▄ ▐█▐█•▐▀▀▪▄▐▀▀▄ ▄▀▀▀█▄\r\n"
				+ "██ ██▌▐█▌▐█▄█▌▐█▄▪▐█▐█▌▐███▌▐█▌▐▌▐█▌.▐▌ ███ ▐█▄▄▌▐█•█▌▐█▄▪▐█\r\n"
				+ "▀▀  █▪▀▀▀ ▀▀▀  ▀▀▀▀ ▀▀▀·▀▀▀ .▀▀▀  ▀█▄▀▪. ▀   ▀▀▀ .▀  ▀ ▀▀▀▀ ");
		sc.close();
	}

}
