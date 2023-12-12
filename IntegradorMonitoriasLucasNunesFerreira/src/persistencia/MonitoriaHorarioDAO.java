package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Disciplina;
import model.Monitor;
import model.MonitoriaHorario;

public class MonitoriaHorarioDAO {
    private ConexaoMysql conexaoMysql;

    public MonitoriaHorarioDAO() {
        conexaoMysql = new ConexaoMysql();
    }

    public long adicionar(MonitoriaHorario monitoriaHorario) {
        conexaoMysql.abrirConexao();
        String sql = "INSERT INTO monitoria_horario VALUES(?, ?, ?, ?, ?, ?, ?)";
        long idMonitoriaHorario = 0;
        try {
            PreparedStatement st = conexaoMysql.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setLong(1, monitoriaHorario.getIdMonitoriaHorario());
            st.setLong(2, monitoriaHorario.getMonitor().getIdMonitor());
            st.setLong(3, monitoriaHorario.getDisciplina().getIdDisciplina());
            st.setString(4, monitoriaHorario.getSala());
            st.setTime(5, java.sql.Time.valueOf(monitoriaHorario.getHoraInicio()));
            st.setTime(6, java.sql.Time.valueOf(monitoriaHorario.getHoraFim()));
            st.setString(7, monitoriaHorario.getDiaSemana());
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                idMonitoriaHorario = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexaoMysql.fecharConexao();
        }
        return idMonitoriaHorario;
    }

    public boolean editar(MonitoriaHorario monitoriaHorario) {
        conexaoMysql.abrirConexao();
        String sql = "UPDATE monitoria_horario SET id_monitor=?, id_disciplina=?, sala=?, hora_fim=?, hora_inicio=?, dia_semana=? WHERE id_monitoria_horario=?";
        boolean foi = false;
        try {
            PreparedStatement st = conexaoMysql.getConexao().prepareStatement(sql);
            st.setLong(1, monitoriaHorario.getMonitor().getIdMonitor());
            st.setLong(2, monitoriaHorario.getDisciplina().getIdDisciplina());
            st.setString(3, monitoriaHorario.getSala());
            st.setTime(4, java.sql.Time.valueOf(monitoriaHorario.getHoraInicio()));
            st.setTime(5, java.sql.Time.valueOf(monitoriaHorario.getHoraFim()));
            st.setString(6, monitoriaHorario.getDiaSemana());
            st.setLong(7, monitoriaHorario.getIdMonitoriaHorario());
            st.executeUpdate();
            if (st.getUpdateCount() > 0) {
                foi = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexaoMysql.fecharConexao();
        }
        return foi;
    }

    public boolean excluir(long idMonitoriaHorario) {
        conexaoMysql.abrirConexao();
        String sql = "DELETE FROM monitoria_horario WHERE id_monitoria_horario=?";
        boolean foi = false;
        try {
            PreparedStatement st = conexaoMysql.getConexao().prepareStatement(sql);
            st.setLong(1, idMonitoriaHorario);
            st.executeUpdate();
            if (st.getUpdateCount() > 0) {
                foi = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexaoMysql.fecharConexao();
        }
        return foi;
    }

    public MonitoriaHorario buscarPorId(long idMonitoriaHorario) {
        conexaoMysql.abrirConexao();
        String sql = "SELECT * FROM monitoria_horario INNER JOIN disciplina ON disciplina.id_disciplina = monitoria_horario.id_disciplina INNER JOIN monitor ON monitor.id_monitor = monitoria_horario.id_monitor WHERE id_monitoria_horario=?";
        MonitoriaHorario monitoriaHorario = null;
        Monitor monitor;
        Disciplina disciplina;
        try {
            PreparedStatement st = conexaoMysql.getConexao().prepareStatement(sql);
            st.setLong(1, idMonitoriaHorario);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                monitoriaHorario = new MonitoriaHorario();
                monitoriaHorario.setIdMonitoriaHorario(rs.getLong("id_monitoria_horario"));
                monitor = new Monitor();
                monitor.setIdMonitor(rs.getLong("monitor.id_monitor"));
                monitor.setMatricula(rs.getString("monitor.matricula"));
                monitor.setNome(rs.getString("monitor.nome"));
                monitoriaHorario.setMonitor(monitor);
                disciplina = new Disciplina();
                disciplina.setIdDisciplina(rs.getLong("disciplina.id_disciplina"));
                disciplina.setNome(rs.getString("disciplina.nome"));
                monitoriaHorario.setDisciplina(disciplina);
                monitoriaHorario.setSala(rs.getString("sala"));
                monitoriaHorario.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                monitoriaHorario.setHoraFim(rs.getTime("hora_fim").toLocalTime());
                monitoriaHorario.setDiaSemana(rs.getString("dia_semana"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexaoMysql.fecharConexao();
        }
        return monitoriaHorario;
    }

    public ArrayList<MonitoriaHorario> buscarPorIdMonitor(long idMonitor) {
        conexaoMysql.abrirConexao();
        String sql = "SELECT * FROM monitoria_horario INNER JOIN disciplina ON monitoria_horario.id_disciplina = disciplina.id_disciplina INNER JOIN monitor ON monitoria_horario.id_monitor = monitor.id_monitor WHERE monitoria_horario.id_monitor=?";
        MonitoriaHorario monitoriaHorario;
        Monitor monitor;
        Disciplina disciplina;
        ArrayList<MonitoriaHorario> monitoriaHorarios;
        monitoriaHorarios = new ArrayList<>();
        try {
            PreparedStatement st = conexaoMysql.getConexao().prepareStatement(sql);
            st.setLong(1, idMonitor);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                monitoriaHorario = new MonitoriaHorario();
                monitoriaHorario.setIdMonitoriaHorario(rs.getLong("id_monitoria_horario"));
                monitor = new Monitor();
                monitor.setIdMonitor(rs.getLong("monitor.id_monitor"));
                monitor.setMatricula(rs.getString("monitor.matricula"));
                monitor.setNome(rs.getString("monitor.nome"));
                monitoriaHorario.setMonitor(monitor);
                disciplina = new Disciplina();
                disciplina.setIdDisciplina(rs.getLong("disciplina.id_disciplina"));
                disciplina.setNome(rs.getString("disciplina.nome"));
                monitoriaHorario.setDisciplina(disciplina);
                monitoriaHorario.setSala(rs.getString("sala"));
                monitoriaHorario.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                monitoriaHorario.setHoraFim(rs.getTime("hora_fim").toLocalTime());
                monitoriaHorario.setDiaSemana(rs.getString("dia_semana"));
                monitoriaHorarios.add(monitoriaHorario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexaoMysql.fecharConexao();
        }
        return monitoriaHorarios;
    }
    
    public ArrayList<MonitoriaHorario> buscarPorIdDisciplina(long idDisciplina) {
        conexaoMysql.abrirConexao();
        String sql = "SELECT * FROM monitoria_horario INNER JOIN disciplina ON monitoria_horario.id_disciplina = disciplina.id_disciplina INNER JOIN monitor ON monitoria_horario.id_monitor = monitor.id_monitor WHERE monitoria_horario.id_disciplina=?";
        MonitoriaHorario monitoriaHorario;
        Monitor monitor;
        Disciplina disciplina;
        ArrayList<MonitoriaHorario> monitoriaHorarios;
        monitoriaHorarios = new ArrayList<>();
        try {
            PreparedStatement st = conexaoMysql.getConexao().prepareStatement(sql);
            st.setLong(1, idDisciplina);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                monitoriaHorario = new MonitoriaHorario();
                monitoriaHorario.setIdMonitoriaHorario(rs.getLong("id_monitoria_horario"));
                monitor = new Monitor();
                monitor.setIdMonitor(rs.getLong("monitor.id_monitor"));
                monitor.setMatricula(rs.getString("monitor.matricula"));
                monitor.setNome(rs.getString("monitor.nome"));
                monitoriaHorario.setMonitor(monitor);
                disciplina = new Disciplina();
                disciplina.setIdDisciplina(rs.getLong("disciplina.id_disciplina"));
                disciplina.setNome(rs.getString("disciplina.nome"));
                monitoriaHorario.setDisciplina(disciplina);
                monitoriaHorario.setSala(rs.getString("sala"));
                monitoriaHorario.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                monitoriaHorario.setHoraFim(rs.getTime("hora_fim").toLocalTime());
                monitoriaHorario.setDiaSemana(rs.getString("dia_semana"));
                monitoriaHorarios.add(monitoriaHorario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexaoMysql.fecharConexao();
        }
        return monitoriaHorarios;
    }

    public ArrayList<MonitoriaHorario> buscarTodos() {
        conexaoMysql.abrirConexao();
        String sql = "SELECT * FROM monitoria_horario";
        MonitoriaHorario monitoriaHorario;
        Monitor monitor;
        Disciplina disciplina;
        ArrayList<MonitoriaHorario> monitoriaHorarios;
        monitoriaHorarios = new ArrayList<>();
        try {
            PreparedStatement st = conexaoMysql.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                monitoriaHorario = new MonitoriaHorario();
                monitoriaHorario.setIdMonitoriaHorario(rs.getLong("id_monitoria_horario"));
                monitor = new Monitor();
                monitor.setIdMonitor(rs.getLong("monitor.id_monitor"));
                monitor.setMatricula(rs.getString("monitor.matricula"));
                monitor.setNome(rs.getString("monitor.nome"));
                monitoriaHorario.setMonitor(monitor);
                disciplina = new Disciplina();
                disciplina.setIdDisciplina(rs.getLong("disciplina.id_disciplina"));
                disciplina.setNome(rs.getString("disciplina.nome"));
                monitoriaHorario.setDisciplina(disciplina);
                monitoriaHorario.setSala(rs.getString("sala"));
                monitoriaHorario.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                monitoriaHorario.setHoraFim(rs.getTime("hora_fim").toLocalTime());
                monitoriaHorario.setDiaSemana(rs.getString("dia_semana"));
                monitoriaHorarios.add(monitoriaHorario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexaoMysql.fecharConexao();
        }
        return monitoriaHorarios;
    }
}
